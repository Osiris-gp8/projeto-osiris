package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.converter.CupomToLayoutCupom;
import br.com.bandtec.osirisapi.converter.EventoToLayoutEvento;
import br.com.bandtec.osirisapi.converter.LayoutCupomToCupom;
import br.com.bandtec.osirisapi.converter.LayoutEventoToEvento;
import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.dto.request.ExportacaoRequest;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.layout.LayoutCupom;
import br.com.bandtec.osirisapi.layout.LayoutEvento;
import br.com.bandtec.osirisapi.layout.LayoutGenerico;
import br.com.bandtec.osirisapi.repository.CupomRepository;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import br.com.bandtec.osirisapi.utils.BucketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArquivoService {

    private final EventoRepository eventoRepository;
    private final CupomRepository cupomRepository;

    private final EventoToLayoutEvento eventoToLayoutEvento;
    private final LayoutEventoToEvento layoutEventoToEvento;

    private final LayoutCupomToCupom layoutCupomToCupom;
    private final CupomToLayoutCupom cupomToLayoutCupom;

    private final BucketService bucket;

    private final UserInfo userInfo;

    //TODO refatorar exportação para usar 'LayoutGenerico'
    public String gerarCsv() {

//        List<LayoutEvento> listaLayout = getAllLayoutEventoByRequest();
        List<LayoutEvento> listaLayout = new ArrayList<>();

        if(listaLayout.isEmpty()){
            throw new ApiRequestException("Não existem eventos", HttpStatus.NO_CONTENT);
        }

        String csv = "";

        for (int i = 0; i < listaLayout.size(); i++) {
            csv += listaLayout.get(i).toCSV();
        }
        return csv;
    }

    public String gerarTxt(ExportacaoRequest request) {

        List<LayoutEvento> layoutEventoList = getLayoutEventosByRequest(request);
        List<LayoutCupom> layoutCupomList = getLayoutCupomsByRequest(request);

        if(layoutEventoList.isEmpty() && layoutCupomList.isEmpty()){
            throw new ApiRequestException("Não existem eventos", HttpStatus.NO_CONTENT);
        }

        Integer qtdRegistros = layoutEventoList.size() + layoutCupomList.size();

        String corpo = montarCorpoPorListasLayout(layoutEventoList, layoutCupomList);

        String txt = montarTxtComCorpo(corpo, qtdRegistros);

        return txt;
    }

    private List<LayoutEvento> getLayoutEventosByRequest(ExportacaoRequest request){
        Integer requestTipoCorpo = request.getTipoCorpo();
        List<LayoutEvento> layoutEventoList;

        if (requestTipoCorpo.equals(ExportacaoRequest.TIPO_CORPO_EVENTO)
            || requestTipoCorpo.equals(ExportacaoRequest.TIPO_CORPO_AMBOS)){

            layoutEventoList = getLayoutEventosByDataInclusaoBetween(request.getDataInicio(), request.getDataFim());
        }else {
            layoutEventoList = Collections.emptyList();
        }

        return layoutEventoList;
    }

    private List<LayoutCupom> getLayoutCupomsByRequest(ExportacaoRequest request){
        Integer requestTipoCorpo = request.getTipoCorpo();
        List<LayoutCupom> layoutCupomList;

        if (requestTipoCorpo.equals(ExportacaoRequest.TIPO_CORPO_CUPOM)
                || requestTipoCorpo.equals(ExportacaoRequest.TIPO_CORPO_AMBOS)){

            layoutCupomList = getLayoutCupomsByDataEmitidoBetween(request.getDataInicio(), request.getDataFim());
        }else {
            layoutCupomList = Collections.emptyList();
        }

        return layoutCupomList;
    }

    private List<LayoutEvento> getLayoutEventosByDataInclusaoBetween(LocalDate dataInicial, LocalDate dataFinal){

        Ecommerce logedEcommerce = userInfo.getUsuario().getEcommerce();

        List<Evento> eventos = eventoRepository
                .findByDataInclusaoBetweenAndEcommerceEquals(dataInicial, dataFinal, logedEcommerce);

        return eventos.stream()
                .map(eventoToLayoutEvento::convert)
                .collect(Collectors.toList());
    }

    private List<LayoutCupom> getLayoutCupomsByDataEmitidoBetween(LocalDate dataInicial, LocalDate dataFinal){
        Ecommerce logedEcommerce = userInfo.getUsuario().getEcommerce();

        List<Cupom> cupoms = cupomRepository.findByDataEmitidoBetweenAndEcommerceEquals(
                dataInicial.atStartOfDay(), dataFinal.atStartOfDay(), logedEcommerce);

        return cupoms.stream()
                .map(cupomToLayoutCupom::convert)
                .collect(Collectors.toList());
    }

    private String montarCorpoPorListasLayout(List<LayoutEvento> layoutEventoList, List<LayoutCupom> layoutCupomList){
        String corpo = "";

        for (LayoutEvento layoutEvento : layoutEventoList) {
            corpo += layoutEvento.toTXT() + System.lineSeparator();
        }

        for (LayoutCupom layoutCupom: layoutCupomList) {
            corpo += layoutCupom.toTXT() + System.lineSeparator();
        }

        return corpo;
    }

    private String montarTxtComCorpo(String corpo, Integer qtdRegistros){
        String txt = "";

        txt += LayoutGenerico.header();
        txt += corpo;
        txt += LayoutGenerico.trailer(qtdRegistros);

        return txt;

    }


    public void importarTXT(BufferedReader conteudo){
        LayoutGenerico layoutGenerico = new LayoutGenerico();

        try {
            layoutGenerico.importarLinhas(conteudo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (layoutGenerico.hasEventoLayout()){
            eventoRepository.saveAll(
                    layoutEventoToEvento.convertFromList( layoutGenerico.getLayoutEventoList() ) );
        }

        if (layoutGenerico.hasCupomLayout()){
            cupomRepository.saveAll(
                    layoutCupomToCupom.convertFromList( layoutGenerico.getLayoutCupomList() ) );
        }

    }

}
