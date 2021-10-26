package br.com.bandtec.osirisapi.converter.implementation;

import br.com.bandtec.osirisapi.converter.S3converter;
import br.com.bandtec.osirisapi.dto.response.S3ArquivoDownloadResponse;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class S3converterImpl implements S3converter {

    @Override
    public List<S3ArquivoDownloadResponse> S3ObjectsToS3ArquivoDownloadResponse(List<S3Object> s3Objects) {

        return s3Objects.stream().map(this::S3ObjectToS3ArquivoDownloadResponse).collect(Collectors.toList());
    }

    private S3ArquivoDownloadResponse S3ObjectToS3ArquivoDownloadResponse(S3Object object){
        return S3ArquivoDownloadResponse.builder()
                .key(object.getKey())
                .metadata(object.getObjectMetadata())
                .httpRequest(object.getObjectContent().getHttpRequest())
                .build();
    }
}
