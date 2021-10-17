package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.converter.implementation.UsuarioConverterImplementation;
import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.response.UsuarioResponse;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfo {

    private final UsuarioConverterImplementation converter;

    private Optional<Usuario> getJwtUserAutenticado() {
        try{
            return getAuthentication()
                    .map(Authentication::getPrincipal)
                    .map(principal -> (Usuario) principal);
        }catch (Exception e){
            throw new ApiRequestException("", HttpStatus.UNAUTHORIZED);
        }

    }

    private Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }

    public UsuarioResponse getUsuario(){
        try {
            return converter.usuarioToUsuarioResponse(getJwtUserAutenticado().get());
        }catch (Exception e){
            throw new ApiRequestException("", HttpStatus.UNAUTHORIZED);
        }
    }
}
