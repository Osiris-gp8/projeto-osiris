package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    public List<Evento> getEventos(){

        List<Evento> eventos = eventoRepository.findAll();

        return eventos;
    }

    public Evento inserirEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void deletarEvento(int idEvento) throws NotFoundException {

        if (!eventoRepository.existsById(idEvento)) {
            throw new NotFoundException("Não existem eventos");
        }

        eventoRepository.deleteById(idEvento);
    }

    public Evento atualizarEvento(Integer idEvento, Evento evento) throws NotFoundException {

        Optional<Evento> eventoParaAtualizarOptional = eventoRepository.findById(idEvento);

        if (!eventoParaAtualizarOptional.isPresent()){
            throw new NotFoundException("Evento não existente");
        }

        Evento eventoParaAtualizar = eventoParaAtualizarOptional.get();

        eventoParaAtualizar.setCupom(evento.getCupom());
        eventoParaAtualizar.setDataCompra(evento.getDataCompra());
        eventoParaAtualizar.setDominioStatus(evento.getDominioStatus());
        eventoParaAtualizar.setIdConsumidorEcommerce(eventoParaAtualizar.getIdConsumidorEcommerce());
        eventoParaAtualizar.setNomeCategoria(evento.getNomeCategoria());
        eventoParaAtualizar.setNomeProduto(evento.getNomeProduto());
        eventoParaAtualizar.setPreco(evento.getPreco());
        eventoParaAtualizar.setEcommerce(evento.getEcommerce());
        eventoParaAtualizar.setCupom(evento.getCupom());

        return eventoRepository.save(eventoParaAtualizar);

    }
}
