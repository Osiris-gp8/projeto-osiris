package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.dto.request.FiltroDataRequest;
import br.com.bandtec.osirisapi.dto.response.EventosComSemCupomResponse;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import br.com.bandtec.osirisapi.service.UserInfo;
import br.com.bandtec.osirisapi.util.MockUtils;
import br.com.bandtec.osirisapi.utils.EventoPilha;
import br.com.bandtec.osirisapi.utils.PilhaObj;
import br.com.bandtec.osirisapi.utils.enums.EventoPilhaEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Optional;

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

    @MockBean
    private UserInfo userInfo;

//    @Test
//    @DisplayName("PUT /{idEvento} - Atualizar um evento existente")
//    void atualizarEventoOk() {
//        int idTeste = 31;
//        Evento evento = new Evento();
//
//        Optional<Evento> optionalEvento = Optional.of(new Evento());
//        Mockito.when(eventoRepository.findById(idTeste))
//                .thenReturn(optionalEvento);
//
//        ResponseEntity resposta =
//                eventoController.atualizarEvento(idTeste, evento);
//
//        assertEquals(200, resposta.getStatusCodeValue());
//    }

    @Test
    @DisplayName("PUT /{idEvento} - Atualizar um evento não existente")
    void atualizarEventoNaoOk() {
        int idTeste = 31;
        Evento evento = MockUtils.getDummyEvento();
        Optional<Evento> optionalEvento = Optional.of(evento);

        MockUtils.mockUserInfo(userInfo);
        Mockito.when(eventoRepository.findById(idTeste))
                .thenReturn(optionalEvento);

        try {
            ResponseEntity resposta =
                    eventoController.atualizarEvento(idTeste, evento);
        } catch(ApiRequestException e) {
            assertEquals(401, e.getStatus().value());
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

    @Test
    @DisplayName("GET /eventos/com-sem-cupom - Quando não existem dados no filtro")
    void getEventosComSemCupomSemDados(){
        FiltroDataRequest request = new FiltroDataRequest(
                LocalDate.now(), LocalDate.now());

       MockUtils.mockUserInfo(userInfo);
        Mockito.when(
                eventoRepository.findByEventoSemCupomAndDataCompraBetween(Mockito.anyInt(), Mockito.any(), Mockito.any())
        ).thenReturn(0);
        Mockito.when(
                eventoRepository.findByEventoComCupomAndDataCompraBetween(Mockito.anyInt(), Mockito.any(), Mockito.any())
        ).thenReturn(0);

        ResponseEntity<EventosComSemCupomResponse> response = eventoController.getSemCupom(request);
        EventosComSemCupomResponse body = response.getBody();

        assertEquals(response.getStatusCodeValue(), 200);
        assertEquals(body.getContagemEventosComCupom(), 0);
        assertEquals(body.getContagemEventosSemCupom(), 0);
    }



    @Test
    @DisplayName("GET /eventos/com-sem-cupom - Quando existem dados no filtro")
    void getEventosComSemCupomComDados(){
        FiltroDataRequest request = new FiltroDataRequest(
                LocalDate.now(), LocalDate.now());

        MockUtils.mockUserInfo(userInfo);
        Mockito.when(
                eventoRepository.findByEventoSemCupomAndDataCompraBetween(Mockito.anyInt(), Mockito.any(), Mockito.any())
        ).thenReturn(153);
        Mockito.when(
                eventoRepository.findByEventoComCupomAndDataCompraBetween(Mockito.anyInt(), Mockito.any(), Mockito.any())
        ).thenReturn(2010);

        ResponseEntity<EventosComSemCupomResponse> response = eventoController.getSemCupom(request);
        EventosComSemCupomResponse body = response.getBody();

        assertEquals(response.getStatusCodeValue(), 200);
        assertEquals(body.getContagemEventosSemCupom(), 153);
        assertEquals(body.getContagemEventosComCupom(), 2010);
    }
}