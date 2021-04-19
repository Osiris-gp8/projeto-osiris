package br.com.bandtec.calculometricas.controller;

import br.com.bandtec.calculometricas.domain.Evento;
import br.com.bandtec.calculometricas.repository.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
@AllArgsConstructor
public class EventoController {

    private final EventoRepository er;

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
