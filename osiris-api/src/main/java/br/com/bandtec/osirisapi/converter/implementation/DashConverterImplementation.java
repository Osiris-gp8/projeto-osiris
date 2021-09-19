package br.com.bandtec.osirisapi.converter.implementation;

import br.com.bandtec.osirisapi.converter.DashConverter;
import br.com.bandtec.osirisapi.dto.response.dash.AcessosVendasDiasResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DashConverterImplementation implements DashConverter {

    @Override
    public AcessosVendasDiasResponse intEventosIntAcessosDataToAcessosVendasResponse(Integer eventos, Integer acessos, LocalDate data) {
        return AcessosVendasDiasResponse.builder()
                .vendas(eventos)
                .acessos(acessos)
                .data(data)
                .diaDaSemana(data.getDayOfWeek().toString()).build();
    }
}
