package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.dto.response.dash.AcessosVendasDiasResponse;

import java.time.LocalDate;

public interface DashConverter {

    AcessosVendasDiasResponse intEventosIntAcessosDataToAcessosVendasResponse(Integer eventos, Integer Acessos, LocalDate data);
}
