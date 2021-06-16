package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.domain.Meta;
import br.com.bandtec.osirisapi.dto.response.MetaResponse;

import java.util.List;

public interface MetaConverter {
    MetaResponse metaToMetaResponse(Meta meta);
    List<MetaResponse> metaListToMetaResponseList(List<Meta> metas);
}
