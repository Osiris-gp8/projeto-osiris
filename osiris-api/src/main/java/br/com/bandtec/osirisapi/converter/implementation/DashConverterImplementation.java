package br.com.bandtec.osirisapi.converter.implementation;

import br.com.bandtec.osirisapi.converter.DashConverter;
import br.com.bandtec.osirisapi.dto.response.dash.AcessosVendasDiasResponse;
import br.com.bandtec.osirisapi.dto.response.dash.RanqueCategoriaResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<RanqueCategoriaResponse> integerListToRanqueCategoriaResponse(List<Integer> ranque) {

        List<String> posicoes = getPosicoes();
        List<RanqueCategoriaResponse> ranqueCategoriaResponseList = new ArrayList<>();

        for (Integer i = 0; i < posicoes.size(); i++){
            RanqueCategoriaResponse ranqueCategoriaResponse =
                    RanqueCategoriaResponse.builder()
                            .quantidade(ranque.get(i))
                            .posisao(posicoes.get(i))
                            .build();
            ranqueCategoriaResponseList.add(ranqueCategoriaResponse);
        }

        return ranqueCategoriaResponseList;
    }

    private List<String> getPosicoes(){

        List<String> posicoes = new ArrayList<String>();

        posicoes.add("primeiro");
        posicoes.add("segundo");
        posicoes.add("terceiro");
        posicoes.add("quarto");
        posicoes.add("quinto");

        return posicoes;
    }
}
