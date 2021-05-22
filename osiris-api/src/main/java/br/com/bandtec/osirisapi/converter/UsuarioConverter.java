package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.response.UsuarioResponse;

import java.util.List;

public interface UsuarioConverter {
    UsuarioResponse usuarioToUsuarioResponse(Usuario usuario);
    List<UsuarioResponse> usuarioListToUsuarioResponseList(List<Usuario> usuarios);
}
