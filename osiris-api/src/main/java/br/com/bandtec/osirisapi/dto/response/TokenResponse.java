package br.com.bandtec.osirisapi.dto.response;

import br.com.bandtec.osirisapi.dto.response.UsuarioResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenResponse {

    private String token;
    private String tipo;
    private UsuarioResponse usuario;

}
