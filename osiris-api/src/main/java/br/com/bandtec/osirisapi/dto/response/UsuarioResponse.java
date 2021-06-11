package br.com.bandtec.osirisapi.dto.response;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import lombok.*;

import javax.persistence.ManyToOne;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioResponse {

    private Integer idUsuario;
    private String nomeCompleto;
    private String loginUsuario;
    private Ecommerce ecommerce;

    @Override
    public String toString() {
        return "UsuarioResponse{" +
                "idUsuario=" + idUsuario +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", loginUsuario='" + loginUsuario + '\'' +
                ", ecommerce=" + ecommerce +
                '}';
    }
}
