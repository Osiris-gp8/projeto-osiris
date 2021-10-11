package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.dto.response.dash.AcessosVendasDiasResponse;
import br.com.bandtec.osirisapi.dto.response.dash.RanqueCategoriaResponse;
import br.com.bandtec.osirisapi.views.RanqueCategoriaView;

import java.time.LocalDate;
import java.util.List;

public interface DashConverter {

    AcessosVendasDiasResponse intEventosIntAcessosDataToAcessosVendasResponse(Integer eventos, Integer Acessos, LocalDate data);

    List<RanqueCategoriaResponse> integerListToRanqueCategoriaResponse(List<Integer> ranque, List<RanqueCategoriaView> nome);
}
