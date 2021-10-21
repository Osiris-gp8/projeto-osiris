package br.com.bandtec.osirisapi.dto.response;

import lombok.Builder;
import lombok.Data;

import java.net.URI;

@Data
@Builder
public class S3ArquivoDownloadResponse {

    private URI uri;
}
