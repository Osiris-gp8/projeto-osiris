package br.com.bandtec.osirisapi.dto.response;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaResponse {

    private Integer idMeta;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    private Double valor;

    private Integer tipo;
    private String labelTipo;

    private Ecommerce ecommerce;

}
