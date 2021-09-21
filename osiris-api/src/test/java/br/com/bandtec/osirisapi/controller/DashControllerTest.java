package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.dto.request.FiltroDataRequest;
import br.com.bandtec.osirisapi.repository.AcessoRepository;
import br.com.bandtec.osirisapi.repository.CupomRepository;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import br.com.bandtec.osirisapi.service.UserInfo;
import br.com.bandtec.osirisapi.util.MockUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DashControllerTest {

    @Autowired
    DashController dashController;

    @MockBean
    EventoRepository eventoRepository;

    @MockBean
    AcessoRepository acessoRepository;

    @MockBean
    UserInfo userInfo;


    @Test
    @DisplayName("GET /contagem-acessos-vendas - Quando a consulta retorna valores")
    void getAcessosVendasUltimosSeteDiasOk() {
        LocalDateTime agora = LocalDateTime.now();

        MockUtils.mockUserInfo(userInfo);
        Mockito.when(acessoRepository.countAllByInicioAcessoAndIdEcommerce(
                agora,
                agora,
                1))
                .thenReturn(800);

        Mockito.when(eventoRepository.countAllByDataCompraAndIdEcommerce(
                agora,
                agora,
                1))
                .thenReturn(100);

        FiltroDataRequest filtroDataRequest = new FiltroDataRequest(agora.toLocalDate(), agora.toLocalDate());
        ResponseEntity response = dashController.getAcessosVendasUltimosSeteDias(filtroDataRequest);

        assertEquals(200, response.getStatusCodeValue());
    }
}