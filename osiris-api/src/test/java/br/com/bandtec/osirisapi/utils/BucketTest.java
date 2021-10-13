package br.com.bandtec.osirisapi.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class BucketTest {

    @Autowired
    Bucket bucket;

    @Test
    void saveAnyObj(){
        File obj = new File("src/test/resources/teste.txt");
        bucket.saveFile(obj.getPath(), obj);
    }

}