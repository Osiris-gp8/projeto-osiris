package br.com.bandtec.calculometricas.controller;

import br.com.bandtec.calculometricas.converter.EventoToLayoutEvento;
import br.com.bandtec.calculometricas.domain.Evento;
import br.com.bandtec.calculometricas.domain.ListaObj;
import br.com.bandtec.calculometricas.layout.LayoutEvento;
import br.com.bandtec.calculometricas.repository.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/arquivos")
public class ArquivosController {

    @Autowired
    private EventoRepository er;
    private EventoToLayoutEvento converter;

    @GetMapping(value = "/relatorio-csv", produces = "text/csv")
    public ResponseEntity downloadCsv(){
        List<Evento> eventos = er.findAll();

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
        return ResponseEntity.ok(registro);
    }

    @GetMapping(value = "/relatorio-txt", produces = "text/plain")
    public ResponseEntity downloadTxt(){
        String txt = "";
        LayoutEvento le = new LayoutEvento(1, 1, "teste", 100.0,
                "teste", LocalDateTime.now(), "teste", "teste", 100.0, "teste");
        txt += LayoutEvento.header();
        txt += le.toTXT();
        txt += LayoutEvento.trailer(1);
        return ResponseEntity.ok(txt);
    }

}
