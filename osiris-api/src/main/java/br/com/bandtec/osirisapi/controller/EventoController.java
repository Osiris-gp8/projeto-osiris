package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.repository.EventoRepository;
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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEvento(@PathVariable int idEvento) {
        if (er.existsById(idEvento)) {
            er.deleteById(idEvento);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping
    public ResponseEntity atualizarEvento(@RequestBody Evento evento){
        if (er.findById(evento.getIdCompra()).isPresent()){
            er.save(evento);
            return ResponseEntity.status(200).build();
        }else {
            return ResponseEntity.status(404).build();
        }
    }

}
