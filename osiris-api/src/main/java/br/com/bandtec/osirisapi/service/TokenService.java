package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.Usuario;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.util.Date;

@Service
public class TokenService {

    @Value("${osiris.jwt.expiration}")
    private String expiration;

    @Value("${osiris.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Integer.parseInt(expiration));

        return Jwts.builder()
                .setIssuer("Api da Osiris")
                .setSubject(logado.getIdUsuario().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
