package br.com.bandtec.osirisapi.utils.hashing;

import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.service.UserInfo;
import br.com.bandtec.osirisapi.utils.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class HashTable {

    private ListaLigada[] tab;
    private final UserInfo userInfo;
    private final BucketService bucket;
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    // Função Hash devolve o número da entrada de um elemento x
    public String funcaoHash(LocalDateTime date){
        Integer ecommerce = userInfo.getUsuario().getEcommerce().getIdEcommerce();
        return ecommerce + "/" + date.format(formatter);
    }


    public void insere(MultipartFile file){
        try {
            String path = funcaoHash(LocalDateTime.now());
            bucket.uploadFile(path, file);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ApiRequestException("File not found", HttpStatus.BAD_REQUEST);
        }
    }

    public ListaLigada<String> buscar(LocalDateTime partition){
        String index = funcaoHash(partition);
        return this.bucket.getFiles(index);
    }
//
//    public boolean remove(Integer num){
//
//        if (!buscar(num)){
//            return false;
//        }
//
//        tab[funcaoHash(num)].removeNode(num);
//        return true;
//    }
}

