package br.com.bandtec.osirisapi.utils;

import br.com.bandtec.osirisapi.utils.hashing.ListaLigada;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class BucketService {

    private final AmazonS3 s3;

    private String bucketName;

    public BucketService() {
        this.s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        this.bucketName = System.getenv("BUCKET_NAME");
    }

    public void uploadFile(String path, MultipartFile multipartFile) throws IOException {
        log.info("Salvando arquivo no bucket: {}", this.bucketName);

        InputStream inputStream = multipartFile.getInputStream();
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(multipartFile.getSize());
        String key = path + "/" + multipartFile.getOriginalFilename();

        PutObjectRequest request = new PutObjectRequest(this.bucketName, key, inputStream, meta);
        this.s3.putObject(request);
    }

    public ListaLigada<String> getFiles(String prefix){
        ListObjectsV2Result result = this.s3.listObjectsV2(this.bucketName, prefix);
        ListaLigada<String> lista = new ListaLigada();

        for(S3ObjectSummary summary : result.getObjectSummaries()) {
            lista.insereNode(summary.getKey());
        }

        return lista;
    }

    public S3Object getFile(String path){

        return s3.getObject(this.bucketName, path);
    }

}
