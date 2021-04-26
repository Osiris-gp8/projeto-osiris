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

        try {
            ecommerceRepository.findById(evento.getFkEcommerce()).orElseThrow( () -> {
                throw new RuntimeException("Id ecommerce não existe");
            });

            cupomRepository.findById( evento.getFkCupom()).orElseThrow( () -> {
                throw new RuntimeException("Id cupom não existe");
            });

            dominioStatusRepository.findById(evento.getFkStatus()).orElseThrow( () -> {
                throw new RuntimeException("Id status não existe");
            });
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }  catch (Throwable e){
            System.err.println(e.getMessage());
        }


        LayoutEvento layout = LayoutEvento.builder()
                .IdCompra( evento.getIdCompra() )
                .IdConsumidor( evento.getIdConsumidorEcommerce() )
                .nomeProduto( evento.getNomeProduto() )
                .precoProduto( evento.getPreco() )
                .categoriaProduto( evento.getNomeCategoria() )
                .dataCompra( evento.getDataCompra() )
                .nomeEcommerce( ecommerceRepository.findById(evento.getFkEcommerce()).get().getNome())
                .nomeCupom( cupomRepository.findById(evento.getFkCupom()).get().getNomeCupom())
                .valorCupom( cupomRepository.findById(evento.getFkCupom()).get().getValor())
                .status( dominioStatusRepository.findById(evento.getFkStatus()).get().getNome())
                .build();

        return layout;

    }
}
