package br.com.bandtec.osirisapi.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UsuarioAcessoRequest {

    private String login;
    private String senha;

}
