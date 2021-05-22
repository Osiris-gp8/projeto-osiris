package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.response.UsuarioResponse;

public interface UsuarioConverter {
    public UsuarioResponse usuarioToUsuarioResponse(Usuario usuario);
}
