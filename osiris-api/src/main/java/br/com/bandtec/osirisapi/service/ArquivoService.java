package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.converter.CupomToLayoutCupom;
import br.com.bandtec.osirisapi.converter.EventoToLayoutEvento;
import br.com.bandtec.osirisapi.converter.LayoutCupomToCupom;
import br.com.bandtec.osirisapi.converter.LayoutEventoToEvento;
import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.layout.LayoutCupom;
import br.com.bandtec.osirisapi.layout.LayoutEvento;
import br.com.bandtec.osirisapi.layout.LayoutGenerico;
import br.com.bandtec.osirisapi.repository.CupomRepository;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    //TODO refatorar exportação para usar 'LayoutGenerico'
    public String gerarCsv() {

        List<LayoutEvento> listaLayout = getAllLayoutEvento();

        if(listaLayout.isEmpty()){
            throw new ApiRequestException("Não existem eventos", HttpStatus.NO_CONTENT);
        }

        String csv = "";

        for (int i = 0; i < listaLayout.size(); i++) {
            csv += listaLayout.get(i).toCSV();
        }
        return csv;
    }

    public String gerarTxt() {


        List<LayoutEvento> layoutEventoList = getAllLayoutEvento();
        List<LayoutCupom> layoutCupomList = getAllLayoutCupom();

        if(layoutEventoList.isEmpty()){
            throw new ApiRequestException("Não existem eventos", HttpStatus.NO_CONTENT);
        }

        String txt = "";
        String corpo = "";

        for (LayoutEvento layoutEvento : layoutEventoList) {
            corpo += layoutEvento.toTXT();
        }

        for (LayoutCupom layoutCupom: layoutCupomList) {
            corpo += layoutCupom.toTXT();
        }

        txt += LayoutGenerico.header();
        txt += corpo;
        txt += LayoutGenerico.trailer(layoutEventoList.size() + layoutCupomList.size());

        return txt;
    }

    public void importarTXT(String conteudo){
        LayoutGenerico layoutGenerico = new LayoutGenerico();

        layoutGenerico.fromTXT(conteudo);

        if (layoutGenerico.hasEventoLayout()){
            eventoRepository.saveAll(
                    layoutEventoToEvento.convertFromList( layoutGenerico.getLayoutEventoList() ) );
        }

        if (layoutGenerico.hasCupomLayout()){
            cupomRepository.saveAll(
                    layoutCupomToCupom.convertFromList( layoutGenerico.getLayoutCupomList() ) );
        }

    }

    public Evento importarEventoTXT(String conteudo){
        LayoutEvento layoutEvento = new LayoutEvento();

        layoutEvento.fromTXT(conteudo);

        Evento evento = layoutEventoToEvento.convert(layoutEvento);

        return evento;
    }

    private List<LayoutEvento> getAllLayoutEvento(){
        List<Evento> eventos = eventoRepository.findAll();

        return eventos.stream()
                .map(eventoToLayoutEvento::convert)
                .collect(Collectors.toList());
    }

    private List<LayoutCupom> getAllLayoutCupom(){
        List<Cupom> cupoms = cupomRepository.findAll();

        return cupoms.stream()
                .map(cupomToLayoutCupom::convert)
                .collect(Collectors.toList());
    }
}
