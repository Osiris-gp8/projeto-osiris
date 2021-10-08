package br.com.bandtec.osirisapi.dto.request;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class NovoUsuarioRequest {

    @Size(min = 5, max = 45)
    private String nomeCompleto;

    @Email
    private String loginUsuario;

    private Ecommerce ecommerce;
}
