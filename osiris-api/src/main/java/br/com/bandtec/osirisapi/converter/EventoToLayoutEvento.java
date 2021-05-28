package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.layout.LayoutEvento;
import br.com.bandtec.osirisapi.repository.CupomRepository;
import br.com.bandtec.osirisapi.repository.DominioStatusRepository;
import br.com.bandtec.osirisapi.repository.EcommerceRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class EventoToLayoutEvento implements Converter<Evento, LayoutEvento> {

    private final EcommerceRepository ecommerceRepository;
    private final CupomRepository cupomRepository;
    private final DominioStatusRepository dominioStatusRepository;

    @Override
    public LayoutEvento convert(Evento evento) {

        LayoutEvento layout = LayoutEvento.builder()
                .IdCompra( evento.getIdEvento() )
                .IdConsumidor( evento.getIdConsumidorEcommerce() )
                .nomeProduto( evento.getNomeProduto() )
                .precoProduto( evento.getPreco() )
                .categoriaProduto( evento.getNomeCategoria() )
                .dataCompra( evento.getDataCompra() )
                .nomeEcommerce(evento.getEcommerce().getNome())
                .nomeCupom( evento.getCupom().getNomeCupom() )
                .valorCupom( evento.getCupom().getValor() )
                .status( evento.getDominioStatus().getNome() )
                .build();

        return layout;

    }
}
