package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.repository.CupomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CupomControllerTest {

    @Autowired
    CupomController cupomController;

    @MockBean
    CupomRepository cupomRepository;

    @Test
    @DisplayName("GET / - Quando há cupons na base")
    void getCuponsOk() {
        List<Cupom> cupomList = Arrays.asList(new Cupom(), new Cupom(), new Cupom());

        Mockito.when(cupomRepository.findAll()).thenReturn(cupomList);

        ResponseEntity<List<Cupom>> resposta = cupomController.getCupons();

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(3, resposta.getBody().size());
    }

    @Test
    @DisplayName("GET / - Quando não há cupons na base")
    void getCuponsNaoOk() {
        Mockito.when(cupomRepository.findAll()).thenReturn(new ArrayList<>());

        try {
            ResponseEntity<List<Cupom>> resposta = cupomController.getCupons();
        } catch (ApiRequestException e) {
            assertEquals(204, e.getStatus().value());
        }
    }

    @Test
    @DisplayName("GET /{idCupom} - Buscar um cupom a partir de um id existente")
    void getCupomOk() {
        int idTeste = 1;
        Optional<Cupom> cupomOptional = Optional.of(new Cupom());

        Mockito.when(cupomRepository.findById(idTeste)).thenReturn(cupomOptional);

        ResponseEntity resposta = cupomController.getCupom(idTeste);

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("POST / - Teste de cadastrar um novo cupom")
    void postCupom() {
        Cupom cupom = new Cupom();

        ResponseEntity resposta = cupomController.postCupom(cupom);

        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("POST /list - Teste de cadastrar uma lista de cupons")
    void postCupons() {
        List<Cupom> cupomList = Arrays.asList(new Cupom(), new Cupom(), new Cupom());

        ResponseEntity resposta = cupomController.postCupons(cupomList);

        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("DELETE /{idCupom} - Teste de deletar um cupom a partir de um id")
    void deleteCupom() {
        int idTeste = 1;

        Optional<Cupom> cupomOptional = Optional.of(new Cupom());

        Mockito.when(cupomRepository.findById(idTeste)).thenReturn(cupomOptional);

        ResponseEntity resposta = cupomController.deleteCupom(idTeste);

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("PUT /{idCupom} - Teste de atualizar um cupom")
    void putCupom() {
        int idTeste = 12;
        Cupom cupom = new Cupom();

        Optional<Cupom> cupomOptional = Optional.of(new Cupom());
        Mockito.when(cupomRepository.findById(idTeste)).thenReturn(cupomOptional);

        ResponseEntity resposta = cupomController.putCupom(idTeste, cupom);
        assertEquals(200, resposta.getStatusCodeValue());
    }
}