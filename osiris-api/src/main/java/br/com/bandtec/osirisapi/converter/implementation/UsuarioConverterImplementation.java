package br.com.bandtec.osirisapi.converter.implementation;

import br.com.bandtec.osirisapi.converter.UsuarioConverter;
import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.request.NovoUsuarioRequest;
import br.com.bandtec.osirisapi.dto.response.UsuarioResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioConverterImplementation implements UsuarioConverter {

    @Override
    public UsuarioResponse usuarioToUsuarioResponse(Usuario usuario) {
        return UsuarioResponse.builder()
                .idUsuario(usuario.getIdUsuario())
                .loginUsuario(usuario.getLoginUsuario())
                .nomeCompleto(usuario.getNomeCompleto())
                .ecommerce(usuario.getEcommerce())
                .build();
    }

    @Override
    public List<UsuarioResponse> usuarioListToUsuarioResponseList(List<Usuario> usuarios) {
        return usuarios
                .stream()
                .map(usuario -> usuarioToUsuarioResponse(usuario))
                .collect(Collectors.toList());
    }

    @Override
    public Usuario novoUsuarioRequestToUsuario(NovoUsuarioRequest request) {
        return Usuario.builder()
                .loginUsuario(request.getLoginUsuario())
                .ecommerce(request.getEcommerce())
                .nomeCompleto(request.getNomeCompleto())
                .build();
    }
}
