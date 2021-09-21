package br.com.bandtec.osirisapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioRecuperarSenhaRequest {

    @NotBlank
    private String senha;
}
