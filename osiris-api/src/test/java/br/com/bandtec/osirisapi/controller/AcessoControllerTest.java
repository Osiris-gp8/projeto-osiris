package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Acesso;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.repository.AcessoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AcessoControllerTest {

    @Autowired
    AcessoController acessoController;

    @MockBean
    AcessoRepository acessoRepository;

    @Test
    @DisplayName("GET / - Quando há acessos na base")
    void getAcessosOk() {
        List<Acesso> acessoList = Arrays.asList(new Acesso(), new Acesso(), new Acesso());

        Mockito.when(acessoRepository.findAll()).thenReturn(acessoList);

        ResponseEntity<List<Acesso>> resposta = acessoController.getAcessos();

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(3, resposta.getBody().size());
    }

    @Test
    @DisplayName("GET / - Quando não há acessos na base")
    void getAcessosNaoOk() {
        Mockito.when(acessoRepository.findAll()).thenReturn(new ArrayList<>());

        try {
            ResponseEntity<List<Acesso>> resposta = acessoController.getAcessos();
        } catch (ApiRequestException e) {
            assertEquals(204, e.getStatus().value());
        }
    }

    @Test
    @DisplayName("POST / - Teste de cadastrar um novo acesso")
    void postAcesso() {
        Acesso acesso = new Acesso();
        acesso.setFimAcesso(LocalDateTime.now().minusHours(2));
        acesso.setInicioAcesso(LocalDateTime.now());
        acesso.setIdConsumidorEcommerce(1);

        ResponseEntity resposta = acessoController.postAcesso(acesso);

        assertEquals(201, resposta.getStatusCodeValue());
    }
}