package br.com.bandtec.osirisapi.converter.implementation;

import br.com.bandtec.osirisapi.converter.ArquivoConverter;
import br.com.bandtec.osirisapi.domain.Arquivo;
import br.com.bandtec.osirisapi.service.UserInfo;
import br.com.bandtec.osirisapi.utils.ArquivoStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class ArquivoConverterImpl implements ArquivoConverter {

    private final UserInfo userInfo;

    private final ArquivoStatusConstants arquivoStatusConstants;

    @Override
    public Arquivo multipartFileToArquivo(MultipartFile multipartFile) {
        return Arquivo.builder()
                .nomeArquivo(multipartFile.getOriginalFilename())
                .tamanhoBytes(multipartFile.getSize())
                .status(arquivoStatusConstants.STATUS_PROCESSANDO)
                .ecommerce(userInfo.getUsuario().getEcommerce())
                .build();
    }
}

