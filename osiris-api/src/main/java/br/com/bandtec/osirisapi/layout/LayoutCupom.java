package br.com.bandtec.osirisapi.layout;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LayoutCupom implements Layout{

    private Integer idCupom;
    private String nomeCupom;
    private Double valor;
    private LocalDateTime dataEmitido;
    private LocalDateTime dataValido;
    private Integer idConsumidor;
    private Boolean usado;
    private Boolean cupomEcommerce;

    private final DateTimeFormatter FORMATO_DATA_HORA = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public String toCSV() {
        return null;
    }

    @Override
    public String toTXT() {
        StringBuilder corpo = new StringBuilder();

        corpo.append( LayoutGenerico.TIPO_REGISTRO_CORPO_CUPOM );
        corpo.append( String.format("%05d", this.idCupom) );
        corpo.append( String.format("%-10s", this.nomeCupom) );
        corpo.append( String.format("%4.2f", this.valor) );
        corpo.append( String.format("%-19s", this.dataEmitido.format(FORMATO_DATA_HORA)) );
        corpo.append( String.format("%-19s", this.dataValido.format(FORMATO_DATA_HORA)) );
        corpo.append( String.format("%05d", this.idConsumidor) );
        corpo.append( String.format("%1d", this.usado ? 1 : 0) );
        corpo.append( String.format("%1d", this.cupomEcommerce ? 1 : 0) );

        return corpo.toString();
    }

    @Override
    public void fromTXT(String conteudo) {
        idCupom = Integer.valueOf(conteudo.substring(2, 7));
        nomeCupom = conteudo.substring(7, 17);
        valor = Double.valueOf(conteudo.substring(17, 21).replace(",", "."));
        dataEmitido = LocalDateTime.parse(conteudo.substring(21, 40), FORMATO_DATA_HORA);
        dataValido = LocalDateTime.parse(conteudo.substring(40, 59), FORMATO_DATA_HORA);
        idConsumidor = Integer.valueOf(conteudo.substring(59, 64));
        usado = Integer.valueOf( conteudo.substring(64, 65) ) == 1;
        cupomEcommerce = Integer.valueOf( conteudo.substring(65, 66) ) == 1;
    }
}
