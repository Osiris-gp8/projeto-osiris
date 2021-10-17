package br.com.bandtec.osirisapi.converter.implementation;

import br.com.bandtec.osirisapi.converter.MetaConverter;
import br.com.bandtec.osirisapi.domain.Meta;
import br.com.bandtec.osirisapi.dto.response.MetaResponse;
import br.com.bandtec.osirisapi.utils.enums.TipoMetaEnum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MetaConverterImplementation implements MetaConverter {


    @Override
    public MetaResponse metaToMetaResponse(Meta meta) {

        return MetaResponse.builder()
                .idMeta(meta.getIdMeta())
                .dataInicio(meta.getDataInicio())
                .dataFim(meta.getDataFim())
                .valor(meta.getValor())
                .ecommerce(meta.getEcommerce())
                .tipo(meta.getTipo())
                .labelTipo( TipoMetaEnum.getDescricaoById(meta.getTipo()) )
                .build();
    }

    @Override
    public List<MetaResponse> metaListToMetaResponseList(List<Meta> metas) {
        return metas.stream().map(this::metaToMetaResponse).collect(Collectors.toList());
    }
}
