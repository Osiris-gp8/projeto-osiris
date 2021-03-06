package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.response.UsuarioResponse;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${osiris.jwt.expiration}")
    private String expiration;

    @Value("${osiris.jwt.secret}")
    private String secret;

    @Value("${osiris.jwt.expiration.password.reset}")
    private String expirationPasswordReset;

    private final Gson gson;

    public String gerarToken(Authentication authentication, HttpServletResponse response) {
        Usuario logado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Integer.parseInt(expiration));

        String token = Jwts.builder()
                .setIssuer("Api da Osiris")
                .setSubject(gson.toJson(logado))
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 30);
        response.addCookie(cookie);

        return token;
    }

    public String gerarTokenAssinado(String info){

        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Integer.parseInt(expirationPasswordReset));

        String token = Jwts.builder()
                .setIssuer("Api da Osiris")
                .setSubject(info)
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        return token;
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Integer getIdUsuario(String token) {
         UsuarioResponse usuario= getUsuarioViaToken(token);

         return usuario.getIdUsuario();
    }

    public UsuarioResponse getUsuarioViaToken(String token){
        return gson.fromJson(Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody().getSubject(),
                UsuarioResponse.class);
    }

    public String getUrlAssinadaViaToken(String token){
        return gson.fromJson(Jwts.parser()
                        .setSigningKey(this.secret)
                        .parseClaimsJws(token)
                        .getBody().getSubject(),
                String.class);
    }


    public String getTokenViaCookie(HttpServletRequest httpRequest){
        Cookie cookie = WebUtils.getCookie(httpRequest, "token");
        if (Objects.isNull(cookie)){
            throw new ApiRequestException("", HttpStatus.UNAUTHORIZED);
        }

        return cookie.getValue();
    }

}
