package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.service.EventoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
@AllArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @GetMapping
    public ResponseEntity getEventos() {

        List<Evento> eventos = eventoService.getEventos();
        if (eventos.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(eventos);
    }

    @PostMapping
    public ResponseEntity postEvento(@RequestBody Evento novoEvento) {

        return ResponseEntity.status(201).body(eventoService.inserirEvento(novoEvento));
    }

    @DeleteMapping("/{idEvento}")
    public ResponseEntity deleteEvento(@PathVariable int idEvento) {
        eventoService.deletarEvento(idEvento);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{idEvento}")
    public ResponseEntity atualizarEvento(
            @PathVariable Integer idEvento,
            @RequestBody Evento evento) {
        return ResponseEntity.status(200).body(eventoService.atualizarEvento(idEvento, evento));
    }

    @PutMapping
    public ResponseEntity putDesfazer(){
        eventoService.desfazerEvento();

        return ResponseEntity.status(200).build();
    }

}
