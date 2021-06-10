package br.com.bandtec.osirisapi.dto;

import br.com.bandtec.osirisapi.dto.response.UsuarioResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenDto {

    private String token;
    private String tipo;
    private UsuarioResponse usuario;

}
