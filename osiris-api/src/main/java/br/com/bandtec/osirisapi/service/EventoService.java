package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.converter.implementation.EventoConverterImplementation;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.domain.EventoProtocolo;
import br.com.bandtec.osirisapi.dto.response.EventoProtocoloResponse;
import br.com.bandtec.osirisapi.dto.response.UsuarioResponse;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.repository.EventoProtocoloRepository;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import br.com.bandtec.osirisapi.schedule.ScheduleService;
import br.com.bandtec.osirisapi.utils.EventoPilha;
import br.com.bandtec.osirisapi.utils.PilhaObj;
import br.com.bandtec.osirisapi.utils.enums.EventoPilhaEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static javafx.scene.input.KeyCode.T;

@Service
@AllArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;
    private final EventoProtocoloRepository protocoloRepository;

    private final TokenService tokenService;
    private final PilhaObj<EventoPilha> eventosPilasObj;
    private final EventoConverterImplementation eventoConverter;
    private final ScheduleService scheduleService;

    public List<Evento> getEventos(HttpServletRequest httpRequest){

        String token = tokenService.getTokenViaCookie(httpRequest);

        UsuarioResponse usuario = tokenService.getUsuarioViaToken(token);

        List<Evento> eventos = eventoRepository.findAllByIdEcommerce(usuario.getEcommerce().getIdEcommerce());

        if (eventos.isEmpty()){
            throw new ApiRequestException("Não existem eventos", HttpStatus.NO_CONTENT);
        }

        return eventos;
    }

    public String inserirEvento(Evento evento, HttpServletRequest httpRequest) {

        if (eventoContemErros(evento, httpRequest) == true){
            throw new ApiRequestException("Não autorizado para adicionar eventos em outro ecommerce",
                    HttpStatus.UNAUTHORIZED);
        }

        String protocolo = UUID.randomUUID().toString();

        evento = setterCamposEvento(evento);
        EventoProtocolo eventoProtocolo = eventoConverter.eventoToEventoProtocolo(evento, protocolo);

        if (scheduleService.getEventosFilaObj().isFull()){
            throw new ApiRequestException("A fila de requisições está cheia", HttpStatus.INSUFFICIENT_STORAGE);
        }

        scheduleService.getEventosFilaObj().insert(eventoProtocolo);

        return protocolo;
    }

    private boolean eventoContemErros(Evento evento, HttpServletRequest httpRequest) {

        UsuarioResponse usuario = tokenService.getUsuarioViaToken(tokenService.getTokenViaCookie(httpRequest));

        if (!evento.getEcommerce().getIdEcommerce().equals(usuario.getEcommerce().getIdEcommerce())){
            return true;
        }

        return false;
    }

    public void deletarEvento(int idEvento, HttpServletRequest httpRequest) {

        String token = tokenService.getTokenViaCookie(httpRequest);
        UsuarioResponse usuario = tokenService.getUsuarioViaToken(token);

        if (!eventoRepository.existsById(idEvento)) {
             throw new ApiRequestException("Esse evento não existe", HttpStatus.NOT_FOUND);
        }

        Evento evento = eventoRepository.findById(idEvento).get();
        if (evento.getEcommerce().getIdEcommerce() != usuario.getEcommerce().getIdEcommerce()){
            throw new ApiRequestException("", HttpStatus.UNAUTHORIZED);
        }

        eventosPilasObj.push(new EventoPilha(idEvento, eventoRepository.findById(idEvento).get() ,"delete"));
        eventoRepository.deleteById(idEvento);
    }

    public Evento atualizarEvento(Integer idEvento, Evento evento) {

        Optional<Evento> eventoParaAtualizarOptional = eventoRepository.findById(idEvento);

        if (!eventoParaAtualizarOptional.isPresent()){
            throw new ApiRequestException("Esse evento não existe", HttpStatus.NOT_FOUND);
        }

        Evento eventoParaAtualizar = eventoParaAtualizarOptional.get();
        //TODO CRIAR UM CONVERTER
        eventoParaAtualizar.setCupom(evento.getCupom());
        eventoParaAtualizar.setDataCompra(evento.getDataCompra());
        eventoParaAtualizar.setDominioStatus(evento.getDominioStatus());
        eventoParaAtualizar.setIdConsumidorEcommerce(eventoParaAtualizar.getIdConsumidorEcommerce());
        eventoParaAtualizar.setNomeCategoria(evento.getNomeCategoria());
        eventoParaAtualizar.setNomeProduto(evento.getNomeProduto());
        eventoParaAtualizar.setPreco(evento.getPreco());
        eventoParaAtualizar.setEcommerce(evento.getEcommerce());
        eventoParaAtualizar.setCupom(evento.getCupom());

        eventosPilasObj.push(new EventoPilha(evento.getIdEvento(), evento,"update"));
        return eventoRepository.save(eventoParaAtualizar);

    }

    public void desfazerEvento() {

        EventoPilha eventoPilha = eventosPilasObj.pop();

        if (Objects.isNull(eventoPilha)){
            throw new ApiRequestException("", HttpStatus.NO_CONTENT);
        }

        if (eventoPilha.getAcao() == EventoPilhaEnum.DELETE){
            eventoRepository.save(eventoPilha.getEventoAntes());
        }

        if (eventoPilha.getAcao() == EventoPilhaEnum.SAVE){
            eventoRepository.deleteById(eventoPilha.getIdEvento());
        }

        if (eventoPilha.getAcao() == EventoPilhaEnum.UPDATE){
            eventoRepository.save(eventoRepository.findById(eventoPilha.getIdEvento()).get());
        }
    }

    private Evento setterCamposEvento(Evento novoEvento){
        novoEvento.setDataInclusao(LocalDate.now());

        return novoEvento;
    }

    public EventoProtocoloResponse getEventoProtocolo(String idProtocolo, HttpServletRequest httpRequest) {

        String token = tokenService.getTokenViaCookie(httpRequest);
        UsuarioResponse usuario = tokenService.getUsuarioViaToken(token);

        Optional<EventoProtocolo> eventoProtocolo = protocoloRepository.findAllByIdEcommerceAndIdProtocolo(
                usuario.getEcommerce().getIdEcommerce(),
                idProtocolo
        );

        if (!eventoProtocolo.isPresent()){
            throw new ApiRequestException("Evento Protocolo não existe", HttpStatus.NOT_FOUND);
        }

        return eventoConverter.eventoProtocoloToEventoProtocoloResponse(eventoProtocolo.get());
    }

    public List<EventoProtocoloResponse> getEventosProtocolo(HttpServletRequest httpRequest) {

        String token = tokenService.getTokenViaCookie(httpRequest);
        UsuarioResponse usuario = tokenService.getUsuarioViaToken(token);

        List<EventoProtocolo> eventosProtocolo = protocoloRepository.findAllEventoProtocoloByIdEcommerce(
                usuario.getEcommerce().getIdEcommerce());

        if (!scheduleService.getEventosFilaObj().isEmpty()){
            for (Integer i = 0; i < scheduleService.getEventosFilaObj().getTamanho(); i++){

                EventoProtocolo eventoProtocolo = scheduleService.getEventosFilaObj().get(i);

                eventosProtocolo.add(eventoProtocolo);
            }
        }

        return eventoConverter.eventoProtocoloToEventoProtocoloResponse(eventosProtocolo);
    }
}
