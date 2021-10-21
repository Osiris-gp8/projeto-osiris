package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.dto.response.S3ArquivoDownloadResponse;

import java.net.URI;

public interface S3converter {

    S3ArquivoDownloadResponse uriToS3ArquivoDownloadResponse(URI uri);
}
