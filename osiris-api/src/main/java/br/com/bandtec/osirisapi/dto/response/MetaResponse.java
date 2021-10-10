package br.com.bandtec.osirisapi.dto.response;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataInicio;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataFim;

    private Double valor;

    private Integer tipo;
    private String labelTipo;

    private Ecommerce ecommerce;

}
