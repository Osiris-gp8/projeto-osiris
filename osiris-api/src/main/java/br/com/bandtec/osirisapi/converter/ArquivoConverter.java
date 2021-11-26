package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.domain.Arquivo;
import org.springframework.web.multipart.MultipartFile;

public interface ArquivoConverter {

    Arquivo multipartFileToArquivo(MultipartFile multipartFile);

}
