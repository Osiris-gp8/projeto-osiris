package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.layout.LayoutCupom;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CupomToLayoutCupom implements Converter<Cupom, LayoutCupom> {
    @Override
    public LayoutCupom convert(Cupom cupom) {
        return LayoutCupom.builder()
                .idCupom(cupom.getIdCupom())
                .idConsumidor(cupom.getIdConsumidorEcommerce())
                .nomeCupom(cupom.getNomeCupom())
                .valor(cupom.getValor())
                .dataEmitido(cupom.getDataEmitido())
                .dataValido(cupom.getDataValidado())
                .usado(cupom.getUsado())
                .cupomEcommerce(cupom.getCupomEcommerce())
                .build();
    }
}
