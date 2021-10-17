package br.com.bandtec.osirisapi.utils;

import br.com.bandtec.osirisapi.utils.hashing.HashTable;
import br.com.bandtec.osirisapi.utils.hashing.ListaLigada;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
@Disabled
class BucketTest {

    @Autowired
    BucketService bucket;

    @Autowired
    HashTable hashTable;

    @Test
    void saveAnyObj(){
        MultipartFile multipartFile = new MockMultipartFile("sourceFile.tmp", "Hello World".getBytes());
        hashTable.insere(multipartFile);
    }

    @Test
    void getAnyObjs(){
        String prefix = "2021-10-15";
        ListaLigada<String> lista = bucket.getFiles(prefix);
        lista.exibe();
    }

}