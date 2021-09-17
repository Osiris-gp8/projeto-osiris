package br.com.bandtec.osirisapi.converter.implementation;

import br.com.bandtec.osirisapi.converter.DashConverter;
import br.com.bandtec.osirisapi.dto.request.dash.AcessosVendasUltimosSeteDias;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DashConverterImplementation implements DashConverter {

    @Override
    public AcessosVendasUltimosSeteDias intEventosIntAcessosDataToAcessosVendasUltimosSeteDias(Integer eventos, Integer acessos, LocalDate data) {
        return AcessosVendasUltimosSeteDias.builder()
                .vendas(eventos)
                .acessos(acessos)
                .data(data)
                .diaDaSemana(data.getDayOfWeek().toString()).build();
    }
}
