package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.domain.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfo {

    private Optional<Usuario> getJwtUserAutenticado() {
        return getAuthentication()
                .map(Authentication::getPrincipal)
                .map(principal -> (Usuario) principal);
    }

    private Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }

    public Integer getIdUsuario() {
        return getJwtUserAutenticado().map(Usuario::getIdUsuario)
                .orElse(null);
    }

    public String getLoginUsuario() {
        return getJwtUserAutenticado().map(Usuario::getLoginUsuario)
                .orElse(null);
    }

    public String getNomeCompleto() {
        return getJwtUserAutenticado().map(Usuario::getNomeCompleto)
                .orElse(null);
    }

    public Ecommerce getEcommerce() {
        return getJwtUserAutenticado().map(Usuario::getEcommerce)
                .orElse(null);
    }
}
