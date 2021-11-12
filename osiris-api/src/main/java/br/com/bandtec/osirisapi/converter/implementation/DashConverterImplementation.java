package br.com.bandtec.osirisapi.converter.implementation;

import br.com.bandtec.osirisapi.converter.DashConverter;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.dto.response.dash.AcessosVendasDiasResponse;
import br.com.bandtec.osirisapi.dto.response.dash.RanqueCategoriaResponse;
import br.com.bandtec.osirisapi.views.RanqueCategoriaView;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<RanqueCategoriaResponse> integerListToRanqueCategoriaResponse(List<RanqueCategoriaView> nomes) {


       return nomes.stream().map(ranqueCategoriaView -> RanqueCategoriaResponse.builder()
          .posisao(String.valueOf(ranqueCategoriaView.getRanque()))
          .categoria(ranqueCategoriaView.getNome())
          .quantidade(ranqueCategoriaView.getQuantidades())
          .build()).collect(Collectors.toList());
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
