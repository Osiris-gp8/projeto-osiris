package br.com.bandtec.osirisapi.dto.response;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.Builder;
import lombok.Data;
import org.apache.http.client.methods.HttpRequestBase;

@Data
@Builder
public class S3ArquivoDownloadResponse {

    private String key;
    private ObjectMetadata metadata;
    private HttpRequestBase httpRequest;
}
