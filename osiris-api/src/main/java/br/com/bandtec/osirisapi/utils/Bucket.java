package br.com.bandtec.osirisapi.utils;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Bucket {

    private final AmazonS3 s3;

    private String bucketName;

    public Bucket() {
        this.s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        this.bucketName = "osiris-arquivos";
    }

    public void saveFile(String path, File file){
        System.out.println(this.bucketName);
        this.s3.putObject(bucketName, path, file);
    }
}
