package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import br.com.bandtec.osirisapi.service.EventoService;
import javassist.NotFoundException;
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
    public ResponseEntity getEventos() throws NotFoundException {
        return ResponseEntity.status(200).body(eventoService.getEventos());
    }

    @PostMapping
    public ResponseEntity postEvento(@RequestBody Evento novoEvento) {

        return ResponseEntity.status(201).body(eventoService.inserirEvento(novoEvento));
    }

    @DeleteMapping("/{idEvento}")
    public ResponseEntity deleteEvento(@PathVariable int idEvento) throws NotFoundException {
        eventoService.deletarEvento(idEvento);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{idEvento}")
    public ResponseEntity atualizarEvento(
            @PathVariable Integer idEvento,
            @RequestBody Evento evento) throws NotFoundException {
        return ResponseEntity.status(200).body(eventoService.atualizarEvento(idEvento, evento));
    }

}
