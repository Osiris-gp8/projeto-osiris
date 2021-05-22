package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.response.UsuarioResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UsuarioConverter {
    UsuarioResponse usuarioToUsuarioResponse(Usuario usuario);
    List<UsuarioResponse> usuarioListToUsuarioResponseList(List<Usuario> usuarios);
}
