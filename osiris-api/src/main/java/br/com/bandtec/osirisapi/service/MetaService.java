package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.converter.MetaConverter;
import br.com.bandtec.osirisapi.domain.Meta;
import br.com.bandtec.osirisapi.dto.response.MetaResponse;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.repository.MetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MetaService {

    private MetaRepository metaRepository;
    private final MetaConverter metaConverter;

    public List<MetaResponse> getMetas() {

        List<Meta> metas = metaRepository.findAll();
        if (metas.isEmpty()) {
            throw new ApiRequestException("Não existem metas", HttpStatus.NO_CONTENT);
        }

        return metaConverter.metaListToMetaResponseList(metas);
    }

    public Meta inserirMeta(Meta meta){

        return metaRepository.save(meta);
    }

    public Meta atualizarMeta(Integer idMeta, Meta meta) {

        Optional<Meta> metaParaAtualizarOptional = metaRepository.findById(idMeta);

        if (!metaParaAtualizarOptional.isPresent()){
            throw new ApiRequestException("Meta não existe", HttpStatus.NOT_FOUND);
        }

        Meta metaParaAtualizar = metaParaAtualizarOptional.get();

        metaParaAtualizar.setValor(meta.getValor());
        metaParaAtualizar.setDataInicio(meta.getDataInicio());
        metaParaAtualizar.setDataFim(meta.getDataFim());

        return metaRepository.save(metaParaAtualizar);
    }

    public void deletarMeta(Integer idMeta) {

        Optional<Meta> metaOptional = metaRepository.findById(idMeta);
        if (!metaOptional.isPresent()){
            throw new ApiRequestException("Meta não existe", HttpStatus.NOT_FOUND);
        }

        metaRepository.deleteById(idMeta);
    }
}
