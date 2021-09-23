package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.converter.DashConverter;
import br.com.bandtec.osirisapi.dto.request.FiltroDataRequest;
import br.com.bandtec.osirisapi.dto.response.dash.AcessosVendasDiasResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DashService {

    private AcessoService acessoService;
    private EventoService eventoService;
    private DashConverter dashConverter;

    public List<AcessosVendasDiasResponse> buscarAcessosVendas(FiltroDataRequest filtroDataRequest) {

        List<AcessosVendasDiasResponse> listaAcessosVendasDiaResponses = new ArrayList<>();

        Integer diferencaEmDias = filtroDataRequest.getDiferencaDatas();

        for (int i = 0; i <= diferencaEmDias; i++){

            LocalDate data = LocalDate.ofYearDay(
                    filtroDataRequest.getDataIncio().getYear(), filtroDataRequest.getDataIncio().getDayOfYear() + i);

            listaAcessosVendasDiaResponses.add(converterDataFiltrada(data));
        }

        return listaAcessosVendasDiaResponses;
    }

    private AcessosVendasDiasResponse converterDataFiltrada(LocalDate data){

            Integer vendasData = eventoService.getEventosPorDia(data);
            Integer acessosData = acessoService.getAcessosPorDia(data);

            return dashConverter.intEventosIntAcessosDataToAcessosVendasResponse(vendasData, acessosData, data);
    }
}
