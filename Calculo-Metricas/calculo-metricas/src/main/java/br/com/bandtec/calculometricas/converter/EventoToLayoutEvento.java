package br.com.bandtec.calculometricas.converter;

import br.com.bandtec.calculometricas.domain.Ecommerce;
import br.com.bandtec.calculometricas.domain.Evento;
import br.com.bandtec.calculometricas.layout.LayoutEvento;
import br.com.bandtec.calculometricas.repository.CupomRepository;
import br.com.bandtec.calculometricas.repository.DominioStatusRepository;
import br.com.bandtec.calculometricas.repository.EcommerceRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

@AllArgsConstructor
public class EventoToLayoutEvento implements Converter<Evento, LayoutEvento> {

    private final EcommerceRepository ecommerceRepository;
    private final CupomRepository cupomRepository;
    private final DominioStatusRepository dominioStatusRepository;

    @Override
    public LayoutEvento convert(Evento evento) {
        LayoutEvento layout = LayoutEvento.builder()
                .IdCompra( evento.getIdCompra() )
                .IdConsumidor( evento.getIdConsumidorEcommerce() )
                .nomeProduto( evento.getNomeProduto() )
                .precoProduto( evento.getPreco() )
                .categoriaProduto( evento.getNomeCategoria() )
                .dataCompra( evento.getDataCompra() )
                // ...

                .build();
        Optional<Ecommerce> opt = ecommerceRepository.findById( evento.getFkEcommerce() );
        opt.orElseThrow(
                () -> {
                    throw new RuntimeException("Id ecommerce não existe");
                }
        );

//        if(!opt.isPresent()){
//            throw  new RuntimeException("");
//        }
        layout.setNomeEcommerce( opt.get().getNome() );

        return layout;

    }
}
