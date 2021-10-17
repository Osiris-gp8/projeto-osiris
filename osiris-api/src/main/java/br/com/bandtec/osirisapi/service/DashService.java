package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.converter.DashConverter;
import br.com.bandtec.osirisapi.dto.request.FiltroDataRequest;
import br.com.bandtec.osirisapi.dto.response.dash.AcessosVendasDiasResponse;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import br.com.bandtec.osirisapi.views.CountAcessoEventos;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DashService {

    private AcessoService acessoService;
    private EventoService eventoService;
    private DashConverter dashConverter;
    private EventoRepository eventoRepository;
    private UserInfo userInfo;

    public List<AcessosVendasDiasResponse> buscarAcessosVendas(FiltroDataRequest filtroDataRequest) {

        List<AcessosVendasDiasResponse> listaAcessosVendasDiaResponses = new ArrayList<>();

        Integer diferencaEmDias = filtroDataRequest.getDiferencaDatas();

        for (int i = 0; i <= diferencaEmDias; i++){

            LocalDate data = LocalDate.ofYearDay(
                    filtroDataRequest.getDataInicio().getYear(), filtroDataRequest.getDataInicio().getDayOfYear() + i);

            listaAcessosVendasDiaResponses.add(converterDataFiltrada(data));
        }

        return listaAcessosVendasDiaResponses;
    }

    private AcessosVendasDiasResponse converterDataFiltrada(LocalDate data){

            Integer vendasData = eventoService.getEventosPorDia(data);
            Integer acessosData = acessoService.getAcessosPorDia(data);

            return dashConverter.intEventosIntAcessosDataToAcessosVendasResponse(vendasData, acessosData, data);
    }

    public List<CountAcessoEventos> countAcessoVendasBetween(FiltroDataRequest filtro){
        LocalDate inicio = filtro.getDataInicio();
        LocalDate fim = filtro.getDataFinal();
        Integer loggedEcommerce = userInfo.getUsuario().getEcommerce().getIdEcommerce();
        List<CountAcessoEventos> result = eventoRepository.countEventosAndAcessosBetween(inicio, fim, loggedEcommerce);
        return result;
    }
}
