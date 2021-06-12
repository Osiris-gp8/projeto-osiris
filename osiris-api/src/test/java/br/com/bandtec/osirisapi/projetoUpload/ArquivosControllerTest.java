package br.com.bandtec.osirisapi.projetoUpload;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ArquivosControllerTest {

    @Autowired
    ArquivosController arquivosController;

    @Test
    void criarArquivo() throws IOException {
        Path path = Paths.get("src", "test", "resources", "testeImportacao.txt");
        String name = "testeImportacao.txt";
        String originalFileName = "testeImportacao.txt";
        String contentType = "text/plain";
        byte[] content = Files.readAllBytes(path);

        MultipartFile multipartFile = new MockMultipartFile(name, originalFileName, contentType, content);

        ResponseEntity resposta = arquivosController.criarArquivo(multipartFile);
        assertEquals(201, resposta.getStatusCodeValue());
    }
}