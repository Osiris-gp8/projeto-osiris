package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import br.com.bandtec.osirisapi.utils.EventoPilha;
import br.com.bandtec.osirisapi.utils.PilhaObj;
import br.com.bandtec.osirisapi.utils.enums.EventoPilhaEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;
    private final PilhaObj<EventoPilha> eventosPilasObj;

    public List<Evento> getEventos(){

        List<Evento> eventos = eventoRepository.findAll();

        if (eventos.isEmpty()){
            throw new ApiRequestException("Não existem eventos", HttpStatus.NO_CONTENT);
        }

        return eventos;
    }

    public Evento inserirEvento(Evento evento) {
        evento = eventoRepository.save(evento);
        eventosPilasObj.push(new EventoPilha(evento.getIdEvento(), null, "save"));
        return evento;
    }

    public void deletarEvento(int idEvento) {

        if (!eventoRepository.existsById(idEvento)) {
            throw new ApiRequestException("Esse evento não existe", HttpStatus.NOT_FOUND);
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
}
