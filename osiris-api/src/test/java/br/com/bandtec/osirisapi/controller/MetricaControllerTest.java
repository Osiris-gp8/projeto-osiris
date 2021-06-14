package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.repository.AcessoRepository;
import br.com.bandtec.osirisapi.repository.CupomRepository;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MetricaControllerTest {

    @Autowired
    MetricaController metricaController;

    @MockBean
    EventoRepository eventoRepository;

    @MockBean
    AcessoRepository acessoRepository;

    @MockBean
    CupomRepository cupomRepository;

    @Test
    void getUltimaSemanaOk() {
        Mockito.when(acessoRepository.countAcessosSemana())
                .thenReturn(10);

        ResponseEntity resposta = metricaController.getUltimaSemana();

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(10, resposta.getBody());
    }

    @Test
    void getUltimaSemanaNaoOk() {
        Mockito.when(acessoRepository.countAcessosSemana())
                .thenReturn(0);

        ResponseEntity resposta = metricaController.getUltimaSemana();

        assertEquals(204, resposta.getStatusCodeValue());
    }

    @Test
    void getVendasAcessoOk() {
        Mockito.when(acessoRepository.count()).thenReturn(new Long(5));
        Mockito.when(eventoRepository.count()).thenReturn(new Long(2));

        ResponseEntity resposta = metricaController.getVendasAcesso();

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(2.5, resposta.getBody());
    }
}