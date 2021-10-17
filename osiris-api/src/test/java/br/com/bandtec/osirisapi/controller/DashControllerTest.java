package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.dto.request.FiltroDataRequest;
import br.com.bandtec.osirisapi.repository.AcessoRepository;
import br.com.bandtec.osirisapi.repository.CupomRepository;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import br.com.bandtec.osirisapi.service.UserInfo;
import br.com.bandtec.osirisapi.util.MockUtils;
import br.com.bandtec.osirisapi.views.CountAcessoEventos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

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
        LocalDate agora = LocalDate.now();

        MockUtils.mockUserInfo(userInfo);

        Mockito.when(
                eventoRepository.countEventosAndAcessosBetween(agora, agora, 1)
        ).thenReturn(
                Collections.singletonList(new CountAcessoEventos() {
                    @Override
                    public String getDiaDaSemana() {
                        return "Tuesday";
                    }

                    @Override
                    public Integer getVendas() {
                        return 100;
                    }

                    @Override
                    public Integer getAcessos() {
                        return 200;
                    }
                })
        );

        FiltroDataRequest filtroDataRequest = new FiltroDataRequest(agora, agora);
        ResponseEntity response = dashController.getAcessosVendasUltimosSeteDias(filtroDataRequest);

        assertEquals(200, response.getStatusCodeValue());
    }
}