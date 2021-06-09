package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.request.UsuarioAcessoRequest;
import br.com.bandtec.osirisapi.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AutenticacaoControllerTest {

    @MockBean
    UsuarioRepository usuarioRepository;

    @MockBean
    AuthenticationManager authManager;

    @Autowired
    AutenticacaoController autenticacaoController;

    @Test
    void autenticarOk() {
        UsuarioAcessoRequest form = new UsuarioAcessoRequest();
        form.setLogin("user");
        form.setSenha("user");

        Usuario usuario = new Usuario();
        usuario.setLoginUsuario("user");
        usuario.setNomeCompleto("user");
        usuario.setSenha("user");
        usuario.setEcommerce(new Ecommerce());

        Optional<Usuario> optionalUsuario = Optional.of(usuario);

        Mockito.when(usuarioRepository.findByLoginUsuario(form.getLogin()))
                .thenReturn(optionalUsuario);

        ResponseEntity resposta = autenticacaoController.autenticar(form);

        assertEquals(200, resposta.getStatusCodeValue());
    }
}