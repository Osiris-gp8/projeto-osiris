package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import org.junit.jupiter.api.Test;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.utils.EventoPilha;
import br.com.bandtec.osirisapi.utils.PilhaObj;
import br.com.bandtec.osirisapi.utils.enums.EventoPilhaEnum;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EventoControllerTest {

    @Autowired
    EventoController eventoController;

    @MockBean
    HttpServletRequest httpServletRequest;

    @MockBean
    EventoRepository eventoRepository;

    @MockBean
    private PilhaObj<EventoPilha> pilhaObj;

    @Test
    @DisplayName("PUT /{idEvento} - Atualizar um evento existente")
    void atualizarEventoOk() {
        int idTeste = 31;
        Evento evento = new Evento();

        Optional<Evento> optionalEvento = Optional.of(new Evento());
        Mockito.when(eventoRepository.findById(idTeste))
                .thenReturn(optionalEvento);

        ResponseEntity resposta =
                eventoController.atualizarEvento(idTeste, evento);

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("PUT /{idEvento} - Atualizar um evento não existente")
    void atualizarEventoNaoOk() {
        int idTeste = 31;
        Evento evento = new Evento();

        Optional<Evento> optionalEvento = Optional.of(new Evento());
        Mockito.when(eventoRepository.findById(idTeste))
                .thenReturn(optionalEvento);

        try {
            ResponseEntity resposta =
                    eventoController.atualizarEvento(idTeste, evento);
        } catch(ApiRequestException e) {
            assertEquals(404, e.getStatus().value());
        }
    }

    @Test
    @DisplayName("PUT /eventos/desfazer - Quando não existem dados na pilha")
    void putDesfazerQuandoNaoExistemEventoNaPilha() {

        Mockito.when(pilhaObj.pop()).thenReturn(null);

        try {
            ResponseEntity responseEntity = eventoController.putDesfazer();
        }catch (ApiRequestException e){
            assertEquals(204, e.getStatus().value());
        }
    }

    @Test
    @DisplayName("PUT /eventos/desfazer - Quando existem dados na pilha - tipo SAVE")
    void putDesfazerUmEventoSave() {

        Integer id = 1;

        Mockito.when(pilhaObj.pop()).thenReturn(new EventoPilha(id, new Evento(), EventoPilhaEnum.SAVE));

        Mockito.when(eventoRepository.findById(id)).thenReturn(Optional.of(new Evento()));

        ResponseEntity responseEntity = eventoController.putDesfazer();

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("PUT /eventos/desfazer - Quando existem dados na pilha - tipo DELETE")
    void putDesfazerUmEventoDelete() {

        Evento evento = new Evento();
        Integer id = 1;

        Mockito.when(pilhaObj.pop()).thenReturn(new EventoPilha(id, evento, EventoPilhaEnum.DELETE));

        Mockito.when(eventoRepository.save(evento)).thenReturn(evento);

        ResponseEntity responseEntity = eventoController.putDesfazer();

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("PUT /eventos/desfazer - Quando existem dados na pilha - tipo UPDATE")
    void putDesfazerUmEventoUpdate() {

        Evento evento = new Evento();
        Integer id = 1;

        Mockito.when(pilhaObj.pop()).thenReturn(new EventoPilha(id, evento, EventoPilhaEnum.UPDATE));

        Mockito.when(eventoRepository.findById(id)).thenReturn(Optional.of(evento));
        Mockito.when(eventoRepository.save(evento)).thenReturn(evento);

        ResponseEntity responseEntity = eventoController.putDesfazer();

        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}