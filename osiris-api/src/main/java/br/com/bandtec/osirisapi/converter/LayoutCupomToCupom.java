package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.layout.LayoutCupom;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LayoutCupomToCupom implements Converter<LayoutCupom, Cupom> {
    @Override
    public Cupom convert(LayoutCupom layoutCupom) {
        Ecommerce ecommerce = new Ecommerce();
        ecommerce.setIdEcommerce(1);
        return Cupom.builder()
                .idCupom(layoutCupom.getIdCupom())
                .idConsumidorEcommerce(layoutCupom.getIdConsumidor())
                .nomeCupom(layoutCupom.getNomeCupom())
                .valor(layoutCupom.getValor())
                .dataEmitido(layoutCupom.getDataEmitido())
                .dataValidado(layoutCupom.getDataValido())
                .usado(layoutCupom.getUsado())
                .cupomEcommerce(layoutCupom.getCupomEcommerce())
                .ecommerce(ecommerce)
                .build();
    }

    public List<Cupom> convertFromList(List<LayoutCupom> layoutCupomList){
        return layoutCupomList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
