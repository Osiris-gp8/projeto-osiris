package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.converter.EventoToLayoutEvento;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.domain.ListaObj;
import br.com.bandtec.osirisapi.layout.LayoutEvento;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/arquivos")
@AllArgsConstructor
public class ArquivosController {

    private final EventoRepository eventoRepository;
    private final EventoToLayoutEvento converter;

    @GetMapping(value = "/relatorio-csv", produces = "text/csv")
    @ResponseBody
    public ResponseEntity downloadCsv(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=exportacao.csv");

        List<Evento> eventos = eventoRepository.findAll();

        if(eventos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        ListaObj<LayoutEvento> listaLayout = new ListaObj<LayoutEvento>(eventos.size());

        String registro = "";
        eventos.forEach( evento -> {
            listaLayout.adicionar(converter.convert(evento));
        });

        for (int i = 0; i < listaLayout.getTamanho(); i++) {
            registro += listaLayout.getElemento(i).toCSV();
        }
        return ResponseEntity.status(200).headers(headers).body(registro);
    }

    @GetMapping(value = "/relatorio-txt", produces = "text/plain")
    @ResponseBody
    public ResponseEntity downloadTxt(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=exportacao.txt");

        List<Evento> eventos = eventoRepository.findAll();

        if(eventos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        ListaObj<LayoutEvento> listaLayout = new ListaObj<LayoutEvento>(eventos.size());

        String txt = "";
        String corpo = "";
        eventos.forEach( evento -> {
            listaLayout.adicionar(converter.convert(evento));
        });

        for (int i = 0; i < listaLayout.getTamanho(); i++) {
            corpo += listaLayout.getElemento(i).toTXT();
        }

        txt += LayoutEvento.header();
        txt += corpo;
        txt += LayoutEvento.trailer(eventos.size());

        return ResponseEntity.status(200).headers(headers).body(txt);
    }

}
