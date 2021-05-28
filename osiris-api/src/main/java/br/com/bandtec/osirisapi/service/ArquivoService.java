package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.converter.EventoToLayoutEvento;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.layout.LayoutEvento;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ArquivoService {

    private final EventoRepository eventoRepository;
    private final EventoToLayoutEvento eventoToLayoutEvento;

    public String gerarCsv() {
        List<Evento> eventos = eventoRepository.findAll();

        if(eventos.isEmpty()){
            throw new ApiRequestException("Não existem eventos", HttpStatus.NO_CONTENT);
        }

        List<LayoutEvento> listaLayout = new ArrayList<LayoutEvento>();

        String csv = "";
        eventos.forEach( evento -> {
            listaLayout.add(eventoToLayoutEvento.convert(evento));
        });

        for (int i = 0; i < listaLayout.size(); i++) {
            csv += listaLayout.get(i).toCSV();
        }
        return csv;
    }

    public String gerarTxt() {
        List<Evento> eventos = eventoRepository.findAll();

        if(eventos.isEmpty()){
            throw new ApiRequestException("Não existem eventos", HttpStatus.NO_CONTENT);
        }

        List<LayoutEvento> listaLayout = new ArrayList<LayoutEvento>();

        String txt = "";
        String corpo = "";
        eventos.forEach( evento -> {
            listaLayout.add(eventoToLayoutEvento.convert(evento));
        });

        for (int i = 0; i < listaLayout.size(); i++) {
            corpo += listaLayout.get(i).toTXT();
        }

        txt += LayoutEvento.header();
        txt += corpo;
        txt += LayoutEvento.trailer(eventos.size());

        return txt;
    }
}
