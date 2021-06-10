package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.converter.implementation.UsuarioConverterImplementation;
import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.TokenDto;
import br.com.bandtec.osirisapi.dto.request.UsuarioAcessoRequest;
import br.com.bandtec.osirisapi.dto.response.UsuarioResponse;
import br.com.bandtec.osirisapi.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;
    private UsuarioConverterImplementation usuarioConverterImplementation;

    @PostMapping
    public ResponseEntity autenticar(
            HttpServletResponse response,
            @RequestBody @Valid UsuarioAcessoRequest form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication, response);
            Usuario usuario = (Usuario) authentication.getPrincipal();
            UsuarioResponse usuarioResponse = usuarioConverterImplementation.usuarioToUsuarioResponse(usuario);

            return ResponseEntity.status(200).body(new TokenDto(token, "Bearer", usuarioResponse));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(400).build();
        }
    }
}