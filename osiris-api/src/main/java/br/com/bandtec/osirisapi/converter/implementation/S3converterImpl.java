package br.com.bandtec.osirisapi.converter.implementation;

import br.com.bandtec.osirisapi.converter.S3converter;
import br.com.bandtec.osirisapi.dto.response.S3ArquivoDownloadResponse;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class S3converterImpl implements S3converter {

    @Override
    public S3ArquivoDownloadResponse uriToS3ArquivoDownloadResponse(URI uri) {
        return S3ArquivoDownloadResponse.builder()
                .uri(uri)
                .build();
    }
}
