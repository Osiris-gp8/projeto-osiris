package br.com.bandtec.calculometricas.controller;

import br.com.bandtec.calculometricas.model.Evento;
import br.com.bandtec.calculometricas.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    EventoRepository er;

    @GetMapping
    public ResponseEntity getEvento() {
        List<Evento> eventos = er.findAll();
        if (eventos.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(eventos);
        }
    }

    @PostMapping
    public ResponseEntity postEvento(@RequestBody Evento novoEvento) {
        er.save(novoEvento);
        return ResponseEntity.status(201).build();
    }

}
