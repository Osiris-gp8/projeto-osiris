package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Meta;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.repository.MetaRepository;
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
class MetaControllerTest {

    @Autowired
    MetaController metaController;

    @MockBean
    MetaRepository metaRepository;

    @Test
    @DisplayName("GET / - Quando há Metas na base")
    void getMetasOk() {
        List<Meta> metaList =
                Arrays.asList(new Meta(), new Meta(), new Meta());

        Mockito.when(metaRepository.findAll())
                .thenReturn(metaList);
        ResponseEntity<List<Meta>> resposta =
                metaController.getMetas();

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("GET / - Quando não há Metas na base")
    void getMetasNaoOk() {
        Mockito.when(metaRepository.findAll())
                .thenReturn(new ArrayList<>());
        try {
            ResponseEntity<List<Meta>> resposta =
                    metaController.getMetas();
        } catch (ApiRequestException e) {
            assertEquals(204, e.getStatus().value());
        }

    }

    @Test
    @DisplayName("POST / - Cadatrar uma nova meta")
    void postMetaOk() {
        Meta meta = new Meta();

        ResponseEntity resposta = metaController.postMeta(meta);

        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("PUT / - Atualizar uma meta")
    void putMetaOk() {
        int idTeste = 31;
        Meta meta = new Meta();

        Optional<Meta> metaOptional = Optional.of(new Meta());
        Mockito.when(metaRepository.findById(idTeste))
                .thenReturn(metaOptional);

        ResponseEntity resposta =
                metaController.putMeta(idTeste, meta);

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("DELETE / - Deletar uma meta")
    void deleteMetaOk() {
        int idTeste = 1;

        Optional<Meta> metaOptional = Optional.of(new Meta());

        Mockito.when(metaRepository.findById(idTeste))
                .thenReturn(metaOptional);

        ResponseEntity resposta =
                metaController.deleteMeta(idTeste);

        assertEquals(200, resposta.getStatusCodeValue());
    }
}