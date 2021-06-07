package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.domain.DominioStatus;
import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.layout.LayoutEvento;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LayoutEventoToEvento implements Converter<LayoutEvento, Evento> {
    private final Integer SEM_CUPOM = 0;

    @Override
    public Evento convert(LayoutEvento layoutEvento) {
        Ecommerce ecommerce = new Ecommerce();
        ecommerce.setIdEcommerce(layoutEvento.getIdEcommerce());
        ecommerce.setNome( layoutEvento.getNomeEcommerce() );

        Cupom cupom = new Cupom();
        //TODO refatorar para criar regra de obrigatoriedade
        if(!layoutEvento.getIdCupom().equals(SEM_CUPOM)){
            cupom.setIdCupom(layoutEvento.getIdCupom());
            cupom.setNomeCupom(layoutEvento.getNomeCupom());
            cupom.setValor(layoutEvento.getValorCupom());
        }else {
            cupom = null;
        }

        DominioStatus dominioStatus = new DominioStatus();
        dominioStatus.setIdDominioStatus(layoutEvento.getIdDominioStatus());
        dominioStatus.setNome(layoutEvento.getStatusNome());

        return Evento.builder()
                .idConsumidorEcommerce(layoutEvento.getIdConsumidor())
                .nomeProduto(layoutEvento.getNomeProduto())
                .preco(layoutEvento.getPrecoProduto())
                .nomeCategoria(layoutEvento.getCategoriaProduto())
                .dataCompra(layoutEvento.getDataCompra())
                .ecommerce(ecommerce)
                .cupom(cupom)
                .dominioStatus(dominioStatus)
                .build();
    }

    public List<Evento> convertFromList(List<LayoutEvento> layoutEventoList){
        return layoutEventoList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
