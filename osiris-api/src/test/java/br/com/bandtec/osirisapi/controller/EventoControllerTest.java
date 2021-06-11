package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventoControllerTest {

    @Autowired
    EventoController eventoController;

    @MockBean
    EventoRepository eventoRepository;

    @Test
    void getEventos() {
        List<Evento> eventoList =
                Arrays.asList(new Evento(), new Evento(), new Evento());

        Mockito.when(eventoRepository.findAll()).thenReturn(eventoList);
        ResponseEntity<List<Evento>> resposta = eventoController.getEventos();

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    void postEvento() {
        Evento evento = new Evento();

        ResponseEntity resposta = eventoController.postEvento(evento);

        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    void deleteEvento() {
        int idTeste = 1;

        Mockito.when(eventoRepository.existsById(idTeste))
                .thenReturn(true);

        ResponseEntity resposta =
                eventoController.deleteEvento(idTeste);

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    void atualizarEvento() {
        int idTeste = 31;
        Evento evento = new Evento();

        Optional<Evento> optionalEvento = Optional.of(new Evento());
        Mockito.when(eventoRepository.findById(idTeste))
                .thenReturn(optionalEvento);

        ResponseEntity resposta =
                eventoController.atualizarEvento(idTeste, evento);

        assertEquals(200, resposta.getStatusCodeValue());
    }
}