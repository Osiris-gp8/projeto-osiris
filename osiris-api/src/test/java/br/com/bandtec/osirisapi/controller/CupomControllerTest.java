package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.repository.CupomRepository;
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
class CupomControllerTest {

    @Autowired
    CupomController cupomController;

    @MockBean
    CupomRepository cupomRepository;

    @Test
    void getCuponsOk() {
        List<Cupom> cupomList = Arrays.asList(new Cupom(), new Cupom(), new Cupom());

        Mockito.when(cupomRepository.findAll()).thenReturn(cupomList);

        ResponseEntity<List<Cupom>> resposta = cupomController.getCupons();

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(3, resposta.getBody().size());
    }

    @Test
    void getCupomOk() {
        int idTeste = 1;
        Optional<Cupom> cupomOptional = Optional.of(new Cupom());

        Mockito.when(cupomRepository.findById(idTeste)).thenReturn(cupomOptional);

        ResponseEntity resposta = cupomController.getCupom(idTeste);

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    void postCupomOk() {
        Cupom cupom = new Cupom();

        ResponseEntity resposta = cupomController.postCupom(cupom);

        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    void postCuponsOk() {
        List<Cupom> cupomList = Arrays.asList(new Cupom(), new Cupom(), new Cupom());

        ResponseEntity resposta = cupomController.postCupons(cupomList);

        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    void deleteCupomOk() {
        int idTeste = 1;

        Optional<Cupom> cupomOptional = Optional.of(new Cupom());

        Mockito.when(cupomRepository.findById(idTeste)).thenReturn(cupomOptional);

        ResponseEntity resposta = cupomController.deleteCupom(idTeste);

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    void putCupomOk() {
        int idTeste = 12;
        Cupom cupom = new Cupom();

        Optional<Cupom> cupomOptional = Optional.of(new Cupom());
        Mockito.when(cupomRepository.findById(idTeste)).thenReturn(cupomOptional);

        ResponseEntity resposta = cupomController.putCupom(idTeste, cupom);
        assertEquals(200, resposta.getStatusCodeValue());
    }
}