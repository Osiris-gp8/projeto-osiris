package br.com.bandtec.osirisapi.utils;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

@Component
@Slf4j
public class Bucket {

    private final AmazonS3 s3;

    private String bucketName;

    public Bucket() {
        this.s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        this.bucketName = "osiris-arquivos";
    }

    public void saveFile(String path, InputStream file){
        log.info("Salvando arquivo no bucket: {}", this.bucketName);
        this.s3.putObject(bucketName, path, file, new ObjectMetadata());
    }
}
