package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.repository.EcommerceRepository;
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
class EcommerceControllerTest {

    @Autowired
    EcommerceController ecommerceController;

    @MockBean
    EcommerceRepository ecommerceRepository;

    @Test
    @DisplayName("GET / - Quando há ecommerces na base")
    void getEcommerceOk() {
        List<Ecommerce> ecommerceList =
                Arrays.asList(new Ecommerce(), new Ecommerce(), new Ecommerce());

        Mockito.when(ecommerceRepository.findAll()).thenReturn(ecommerceList);
        ResponseEntity<List<Ecommerce>> resposta = ecommerceController.getEcommerce();

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("GET / - Quando há ecommerces na base")
    void getEcommerceNaoOk() {
        Mockito.when(ecommerceRepository.findAll()).thenReturn(new ArrayList<>());

        try {
            ResponseEntity<List<Ecommerce>> resposta = ecommerceController.getEcommerce();
        } catch (ApiRequestException e) {
            assertEquals(204, e.getStatus().value());
        }

    }

    @Test
    @DisplayName("POST / - Cadastrar um ecommerce")
    void postEcommerce() {
        Ecommerce ecommerce = new Ecommerce();

        ResponseEntity resposta = ecommerceController.postEcommerce(ecommerce);

        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("DELETE /{id} - Deletar um ecommerce por um id existente")
    void deleteEcommerceOk() {
        int idTeste = 1;

        Mockito.when(ecommerceRepository.existsById(idTeste))
                .thenReturn(true);

        ResponseEntity resposta =
                ecommerceController.deleteEcommerce(idTeste);

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("DELETE /{idEcommerce} - Deletar um ecommerce por um id não existente")
    void deleteEcommerceNaoOk() {
        int idTeste = 1;

        Mockito.when(ecommerceRepository.existsById(idTeste))
                .thenReturn(false);

        try {
            ResponseEntity resposta =
                ecommerceController.deleteEcommerce(idTeste);
        } catch (ApiRequestException e) {
            assertEquals(404, e.getStatus().value());
        }

    }

    @Test
    @DisplayName("PUT /{idEcommerce} - Atualizar um ecommerce")
    void atualizarEcommerce() {
        int idTeste = 31;
        Ecommerce ecommerce = new Ecommerce();

        Optional<Ecommerce> optionalEcommerce = Optional.of(new Ecommerce());
        Mockito.when(ecommerceRepository.findById(idTeste))
                .thenReturn(optionalEcommerce);

        ResponseEntity resposta =
                ecommerceController.atualizarEcommerce(idTeste, ecommerce);

        assertEquals(200, resposta.getStatusCodeValue());
    }
}