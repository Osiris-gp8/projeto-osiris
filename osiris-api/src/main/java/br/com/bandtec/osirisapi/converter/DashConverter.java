package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.dto.response.dash.AcessosVendasDiasResponse;
import br.com.bandtec.osirisapi.dto.response.dash.RanqueCategoriaResponse;

import java.time.LocalDate;
import java.util.List;

public interface DashConverter {

    AcessosVendasDiasResponse intEventosIntAcessosDataToAcessosVendasResponse(Integer eventos, Integer Acessos, LocalDate data);

    List<RanqueCategoriaResponse> integerListToRanqueCategoriaResponse(List<Integer> ranque);
}
