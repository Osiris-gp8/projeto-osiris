package br.com.bandtec.osirisapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@SpringBootTest
class BucketServiceTest {

    @Autowired
    private BucketService bucketService;

    @Test
    void saveToS3() {
        MultipartFile multipartFile = new MockMultipartFile("sourceFile.tmp", "Hello World".getBytes());

        try {
            File file = bucketService.saveToS3(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}