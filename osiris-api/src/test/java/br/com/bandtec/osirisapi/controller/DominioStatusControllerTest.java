package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.DominioStatus;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.repository.DominioStatusRepository;
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
class DominioStatusControllerTest {

    @Autowired
    DominioStatusController dominioStatusController;

    @MockBean
    DominioStatusRepository dominioStatusRepository;

    @Test
    @DisplayName("GET / - Quando há status na base")
    void getDominioStatusOk() {
        List<DominioStatus> dominioStatusList =
                Arrays.asList(new DominioStatus(), new DominioStatus(), new DominioStatus());

        Mockito.when(dominioStatusRepository.findAll()).thenReturn(dominioStatusList);

        ResponseEntity<List<DominioStatus>> resposta = dominioStatusController.getDominioStatus();

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(3, resposta.getBody().size());
    }

    @Test
    @DisplayName("GET / - Quando não há status na base")
    void getDominioStatusNaoOk() {
        Mockito.when(dominioStatusRepository.findAll()).thenReturn(new ArrayList<>());

        try {
            ResponseEntity<List<DominioStatus>> resposta = dominioStatusController.getDominioStatus();
        } catch (ApiRequestException e) {
            assertEquals(204, e.getStatus().value());
        }
    }

    @Test
    @DisplayName("POST / - Cadastro de um status de compra")
    void postDominioStatus() {
        DominioStatus dominioStatus = new DominioStatus();

        ResponseEntity resposta = dominioStatusController.postDominioStatus(dominioStatus);

        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("DELETE /{idDominioStatus} - Deletar um status de compra por um id existente")
    void deleteDominioStatusOk() {
        int idTeste = 1;
        Mockito.when(dominioStatusRepository.existsById(idTeste)).thenReturn(true);
        ResponseEntity resposta = dominioStatusController.deleteDominioStatus(idTeste);
        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("DELETE /{idDominioStatus} - Deletar um status de compra por um id não existente")
    void deleteDominioStatusNaoOk() {
        int idTeste = 1;
        Mockito.when(dominioStatusRepository.existsById(idTeste)).thenReturn(false);

        try {
            ResponseEntity resposta = dominioStatusController.deleteDominioStatus(idTeste);
        } catch (ApiRequestException e) {
            assertEquals(404, e.getStatus().value());
        }
    }

    @Test
    @DisplayName("PUT /{idDominioStatus} - Teste de atualizar um status")
    void atualizarDominioStatus() {
        int idTeste = 12;
        DominioStatus dominioStatus = new DominioStatus();
        Optional<DominioStatus> dominioStatusOptional = Optional.of(new DominioStatus());

        Mockito.when(dominioStatusRepository.findById(idTeste))
                .thenReturn(dominioStatusOptional);

        ResponseEntity resposta =
                dominioStatusController.atualizarDominioStatus(idTeste, dominioStatus);

        assertEquals(200, resposta.getStatusCodeValue());
    }
}