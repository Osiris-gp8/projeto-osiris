package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.request.UsuarioAtualizacaoRequest;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.repository.EcommerceRepository;
import br.com.bandtec.osirisapi.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioControllerTest {

    @Autowired
    UsuarioController usuarioController;

    @MockBean
    UsuarioRepository usuarioRepository;

    @MockBean
    EcommerceRepository ecommerceRepository;

    @Test
    @DisplayName("GET / - Quando há usuários na base")
    void getUsuarioOk() {
        List<Usuario> usuarioList =
                Arrays.asList(new Usuario(), new Usuario(), new Usuario());

        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarioList);
        ResponseEntity<List<Usuario>> resposta = usuarioController.getUsuario();

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("GET / - Quando não há usuários na base")
    void getUsuarioNaoOk() {
        Mockito.when(usuarioRepository.findAll()).thenReturn(new ArrayList<>());

        try {
            ResponseEntity<List<Usuario>> resposta = usuarioController.getUsuario();
        } catch (ApiRequestException e) {
            assertEquals(204, e.getStatus().value());
        }
    }

    @Test
    @DisplayName("POST / - Cadastrar Usuário")
    void postUsuario() {
        Ecommerce ecommerce = new Ecommerce();
        ecommerce.setIdEcommerce(6);
        ecommerce.setNome("TESTE");
        ecommerce.setCnpj("99.999.999/9999-99");

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(82);
        usuario.setNomeCompleto("USER_TESTE");
        usuario.setLoginUsuario("USER_TESTE");
        usuario.setSenha("12345");
        usuario.setEcommerce(ecommerce);

        Mockito.when(ecommerceRepository.existsById(usuario.getEcommerce().getIdEcommerce()))
                .thenReturn(true);

        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);

        ResponseEntity resposta = usuarioController.postUsuario(usuario);

        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("DELETE /{idUsuario} - Deletar usuário por um id existente")
    void deleteUsuarioOk() {
        int idTeste = 41;

        Mockito.when(usuarioRepository.existsById(idTeste)).thenReturn(true);

        ResponseEntity resposta = usuarioController.deleteUsuario(idTeste);

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("DELETE /{idUsuario} - Deletar usuário por um id não existente")
    void deleteUsuarioNaoOk() {
        int idTeste = 41;

        Mockito.when(usuarioRepository.existsById(idTeste)).thenReturn(false);

        try {
            ResponseEntity resposta = usuarioController.deleteUsuario(idTeste);
        } catch (ApiRequestException e) {
            assertEquals(404, e.getStatus().value());
        }


    }

    @Test
    @DisplayName("PUT /{idUsuario} - Atualizar um usuário")
    void putUsuario() {
        int idTeste = 31;
        Ecommerce ecommerce = new Ecommerce();
        ecommerce.setIdEcommerce(6);
        ecommerce.setNome("TESTE");
        ecommerce.setCnpj("99.999.999/9999-99");

        UsuarioAtualizacaoRequest usuario = new UsuarioAtualizacaoRequest();
        usuario.setNomeCompleto("USER_TESTE");
        usuario.setLoginUsuario("USER_TESTE");

        Optional<Usuario> usuarioParaAtualizarOptional = Optional.of(new Usuario());
        Usuario usuarioParaAtualizar = usuarioParaAtualizarOptional.get();

        Mockito.when(usuarioRepository.save(usuarioParaAtualizar)).thenReturn(usuarioParaAtualizar);
        Mockito.when(usuarioRepository.findById(idTeste)).thenReturn(usuarioParaAtualizarOptional);

        ResponseEntity resposta =
                usuarioController.putUsuario(idTeste, usuario);

        assertEquals(201, resposta.getStatusCodeValue());
    }
}