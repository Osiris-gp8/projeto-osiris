package br.com.bandtec.osirisapi.dto.response;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioResponse {

    private Integer idUsuario;
    private String nomeCompleto;
    private String loginUsuario;

    @ManyToOne
    private Ecommerce ecommerce;
}
