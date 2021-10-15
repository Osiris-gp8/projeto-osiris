package br.com.bandtec.osirisapi.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
class BucketTest {

    @Autowired
    Bucket bucket;

    @Test
    void saveAnyObj() throws IOException {
        MultipartFile multipartFile = new MockMultipartFile("sourceFile.tmp", "Hello World".getBytes());

        InputStream inputStream;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException(e.getCause());
        }

        bucket.saveFile("src/test/resources/teste.txt", inputStream);
    }

}