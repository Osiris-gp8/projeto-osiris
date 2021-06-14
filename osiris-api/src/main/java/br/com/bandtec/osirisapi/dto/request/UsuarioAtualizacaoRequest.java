package br.com.bandtec.osirisapi.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
public class UsuarioAtualizacaoRequest {

    @NotBlank
    @Size(min = 5, max = 45)
    private String nomeCompleto;

    @NotBlank
    @Size(min = 4, max = 8)
    private String loginUsuario;
    
}
