package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.dto.response.UsuarioResponse;
import br.com.bandtec.osirisapi.utils.Bucket;
import br.com.bandtec.osirisapi.utils.hashing.HashTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class BucketService {

    private  final UserInfo userInfo;

    public File saveToS3(MultipartFile multipartFile) throws IOException {

        Bucket bucket = new Bucket();

        InputStream inputStream = multipartFileToInputStream(multipartFile);

        bucket.saveFile();
    }

    private InputStream multipartFileToInputStream(MultipartFile multipartFile) throws IOException {
        return multipartFile.getInputStream();
    }

    private String getPath(){
        UsuarioResponse usuario = userInfo.getUsuario();

        HashTable hashTable = new HashTable();

        String path = usuario.getEcommerce().getIdEcommerce().toString();
    }
}
