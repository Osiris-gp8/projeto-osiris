package br.com.bandtec.osirisapi.controller;

import java.net.URI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class AutenticacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /auth - Testando autenticação caso o login e senha estejam incorretos")
    void autenticarLoginInvalidoESenhaInvalida() throws Exception {
        URI path = new URI("/auth");
        String json = "{\"login\": \"usuario inválido\", \"senha\": \"senha inválida\"}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(path)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400)
                );
    }

    @Test
    @DisplayName("POST /auth - Testando autenticação caso o login esteja correto e senha esteja incorreta")
    void autenticarLoginCorretoESenhaInvalida() throws Exception {
        URI path = new URI("/auth");
        String json = "{\"login\": \"user8\", \"senha\": \"senha inválida\"}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(path)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400)
                );
    }

    @Test
    @DisplayName("POST /auth - Testando autenticação caso o login esteja incorreto e senha esteja correta")
    void autenticarLoginInvalidoESenhaCorreta() throws Exception {
        URI path = new URI("/auth");
        String json = "{\"login\": \"usuario inválido\", \"senha\": \"user8\"}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(path)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400)
                );
    }

    @Test
    @DisplayName("POST /auth - Testando autenticação caso o login e senha esteja incorreta")
    void autenticarLoginCorretoESenhaCorreta() throws Exception {
        URI path = new URI("/auth");
        String json = "{\"login\": \"user8\", \"senha\": \"user8\"}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(path)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200)
                );
    }
}