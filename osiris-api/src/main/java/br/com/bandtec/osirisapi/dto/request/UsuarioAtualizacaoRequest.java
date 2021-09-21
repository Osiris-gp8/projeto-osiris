package br.com.bandtec.osirisapi.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
public class UsuarioAtualizacaoRequest {

    @Size(min = 5, max = 45)
    private String nomeCompleto;

    @Size(min = 4, max = 8)
    private String loginUsuario;

    @NotBlank
    private String email;
}
