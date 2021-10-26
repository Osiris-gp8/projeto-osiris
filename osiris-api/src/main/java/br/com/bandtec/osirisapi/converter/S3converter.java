package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.dto.response.S3ArquivoDownloadResponse;
import com.amazonaws.services.s3.model.S3Object;

import java.util.List;

public interface S3converter {

    List<S3ArquivoDownloadResponse> S3ObjectsToS3ArquivoDownloadResponse(List<S3Object> s3Object);
}
