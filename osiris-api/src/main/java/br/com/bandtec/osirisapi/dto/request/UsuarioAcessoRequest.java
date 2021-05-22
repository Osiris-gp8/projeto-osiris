package br.com.bandtec.osirisapi.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UsuarioAcessoRequest {

    private String login;
    private String senha;

}
