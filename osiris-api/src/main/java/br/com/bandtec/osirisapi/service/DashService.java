package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.converter.DashConverter;
import br.com.bandtec.osirisapi.dto.request.dash.AcessosVendasUltimosSeteDias;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DashService {

    private AcessoService acessoService;
    private EventoService eventoService;
    private DashConverter dashConverter;

    public List<AcessosVendasUltimosSeteDias> buscarAcessosVendasUltimosSeteDias() {

        List<AcessosVendasUltimosSeteDias> listaAcessosVendasUltimosSeteDias = new ArrayList<>();

        for (int i = 6; i >= 0; i--){

            LocalDate data = LocalDate.of(
                    LocalDate.now().getYear(),
                    LocalDate.now().getMonthValue(),
                    LocalDate.now().getDayOfMonth() - i);

            listaAcessosVendasUltimosSeteDias.add(rebeceDataRetornaAcessosVendasUltimosSeteDias(data));
        }

        return listaAcessosVendasUltimosSeteDias;
    }

    private AcessosVendasUltimosSeteDias rebeceDataRetornaAcessosVendasUltimosSeteDias(LocalDate data){

            Integer vendasData = eventoService.getEventosPorDia(data);
            Integer acessosData = acessoService.getAcessosPorDia(data);

            return dashConverter.intEventosIntAcessosDataToAcessosVendasUltimosSeteDias(vendasData, acessosData, data);
    }
}
