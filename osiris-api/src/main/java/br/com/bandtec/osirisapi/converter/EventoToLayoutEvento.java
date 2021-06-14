package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.layout.LayoutEvento;
import br.com.bandtec.osirisapi.repository.CupomRepository;
import br.com.bandtec.osirisapi.repository.DominioStatusRepository;
import br.com.bandtec.osirisapi.repository.EcommerceRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;


@AllArgsConstructor
@Component
public class EventoToLayoutEvento implements Converter<Evento, LayoutEvento> {

    private final EcommerceRepository ecommerceRepository;
    private final CupomRepository cupomRepository;
    private final DominioStatusRepository dominioStatusRepository;

    @Override
    public LayoutEvento convert(Evento evento) {


        Optional<Cupom> cupomOpt = Optional.ofNullable(evento.getCupom());
        Cupom cupom;
        if (!cupomOpt.isPresent()){
            cupom = createEmptyCupom();
        }else {
            cupom = cupomOpt.get();
        }

        LayoutEvento layout = LayoutEvento.builder()
                .IdEvento( evento.getIdEvento() )
                .IdConsumidor( evento.getIdConsumidorEcommerce() )
                .nomeProduto( evento.getNomeProduto() )
                .precoProduto( evento.getPreco() )
                .categoriaProduto( evento.getNomeCategoria() )
                .dataCompra( evento.getDataCompra() )
                .idEcommerce( evento.getEcommerce().getIdEcommerce() )
                .nomeEcommerce(evento.getEcommerce().getNome())
                .idCupom( cupom.getIdCupom() )
                .nomeCupom(cupom.getNomeCupom())
                .valorCupom( cupom.getValor() )
                .idDominioStatus( evento.getDominioStatus().getIdDominioStatus() )
                .statusNome( evento.getDominioStatus().getNome() )
                .build();

        return layout;
    }

    private Cupom createEmptyCupom(){
        return Cupom.builder()
                .idCupom(0)
                .nomeCupom("")
                .valor(0.0)
                .idConsumidorEcommerce(0)
                .cupomEcommerce(false)
                .dataEmitido( null )
                .usado(false)
                .build();
    }
}
