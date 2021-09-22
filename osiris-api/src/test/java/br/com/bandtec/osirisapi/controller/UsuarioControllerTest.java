package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.request.UsuarioAtualizacaoRequest;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.repository.EcommerceRepository;
import br.com.bandtec.osirisapi.repository.UsuarioRepository;
import br.com.bandtec.osirisapi.service.UserInfo;
import br.com.bandtec.osirisapi.util.MockUtils;
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

    @MockBean
    UserInfo userInfo;

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
        MockUtils.mockUserInfo(userInfo);

        Integer idTeste = 1;
        Ecommerce ecommerce = new Ecommerce();
        ecommerce.setIdEcommerce(1);
        ecommerce.setNome("TESTE");
        ecommerce.setCnpj("99.999.999/9999-99");

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idTeste);
        usuario.setLoginUsuario("USER_TESTE");
        usuario.setNomeCompleto("USER_TESTE");
        usuario.setEcommerce(ecommerce);

        UsuarioAtualizacaoRequest usuarioAtualizacaoRequest = new UsuarioAtualizacaoRequest();
        usuarioAtualizacaoRequest.setNomeCompleto("USER_TESTE");
        usuarioAtualizacaoRequest.setLoginUsuario("USER_TESTE");

        Optional<Usuario> usuarioParaAtualizarOptional = Optional.of(usuario);
        Usuario usuarioParaAtualizar = usuarioParaAtualizarOptional.get();

        Mockito.when(usuarioRepository.saveAndFlush(usuarioParaAtualizar)).thenReturn(usuarioParaAtualizar);
        Mockito.when(usuarioRepository.findById(Mockito.anyInt())).thenReturn(usuarioParaAtualizarOptional);

        ResponseEntity resposta =
                usuarioController.putUsuario(idTeste, usuarioAtualizacaoRequest);

        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("GET /usuarios/eecommerce/1 - Quando usuário é autorizado")
    void getUsuariosPorEcommerceOk() {

        MockUtils.mockUserInfo(userInfo);

        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario();
        usuarios.add(usuario);

        Mockito.when(usuarioRepository.findAllByEcommerceIdEcommerce(Mockito.anyInt())).thenReturn(usuarios);
        Mockito.when(ecommerceRepository.existsById(Mockito.anyInt())).thenReturn(true);

        ResponseEntity response = usuarioController.getUsuariosPorEcommerce(1);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("GET /usuarios/eecommerce/1 - Quando usuário não é autorizado")
    void getUsuariosPorEcommerceSemAutorizacao() {

        MockUtils.mockUserInfo(userInfo);

        assertThrows(ApiRequestException.class, () -> usuarioController.getUsuariosPorEcommerce(2));
    }

    @Test
    @DisplayName("GET /usuarios/eecommerce/500 - Quando ecommerce não existe")
    void getUsuariosPorEcommerceEcommerceNaoExiste() {

        MockUtils.mockUserInfo(userInfo);

        Mockito.when(ecommerceRepository.existsById(Mockito.anyInt())).thenReturn(false);

        assertThrows(ApiRequestException.class, () -> usuarioController.getUsuariosPorEcommerce(500));
    }
}