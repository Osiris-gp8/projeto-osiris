package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.repository.EcommerceRepository;
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
class EcommerceControllerTest {

    @Autowired
    EcommerceController ecommerceController;

    @MockBean
    EcommerceRepository ecommerceRepository;

    @Test
    void getEcommerce() {
        List<Ecommerce> ecommerceList =
                Arrays.asList(new Ecommerce(), new Ecommerce(), new Ecommerce());

        Mockito.when(ecommerceRepository.findAll()).thenReturn(ecommerceList);
        ResponseEntity<List<Ecommerce>> resposta = ecommerceController.getEcommerce();

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    void postEcommerce() {
        Ecommerce ecommerce = new Ecommerce();

        ResponseEntity resposta = ecommerceController.postEcommerce(ecommerce);

        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    void deleteEcommerce() {
        int idTeste = 1;

        Mockito.when(ecommerceRepository.existsById(idTeste))
                .thenReturn(true);

        ResponseEntity resposta =
                ecommerceController.deleteEcommerce(idTeste);

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
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