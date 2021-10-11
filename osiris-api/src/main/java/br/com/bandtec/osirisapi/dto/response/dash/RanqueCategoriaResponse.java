package br.com.bandtec.osirisapi.dto.response.dash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RanqueCategoriaResponse {

    private String posisao;
    private Integer quantidade;
}
