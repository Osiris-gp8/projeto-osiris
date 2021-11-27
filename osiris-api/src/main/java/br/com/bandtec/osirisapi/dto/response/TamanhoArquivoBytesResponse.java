package br.com.bandtec.osirisapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TamanhoArquivoBytesResponse {

    private Long tamanhoEmBytes;
}
