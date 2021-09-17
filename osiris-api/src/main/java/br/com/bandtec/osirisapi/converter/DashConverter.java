package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.dto.request.dash.AcessosVendasUltimosSeteDias;

import java.time.LocalDate;

public interface DashConverter {

    AcessosVendasUltimosSeteDias intEventosIntAcessosDataToAcessosVendasUltimosSeteDias(Integer eventos, Integer Acessos, LocalDate data);
}
