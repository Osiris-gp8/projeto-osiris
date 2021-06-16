package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.service.EventoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/eventos")
@AllArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @GetMapping
    public ResponseEntity getEventos() {

        return ResponseEntity.status(200).body(eventoService.getEventos());
    }

    @PostMapping
    public ResponseEntity postEvento(@RequestBody @Valid Evento novoEvento, HttpServletRequest httpRequest) {

        return ResponseEntity.status(202).header(
                "protocolo",
                eventoService.inserirEventoAssincrono(novoEvento)).build();
    }

    @PostMapping("/list")
    public ResponseEntity postEventos(@RequestBody List<@Valid Evento> eventoList){
        eventoList.forEach(eventoService::inserirEvento);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{idEvento}")
    public ResponseEntity deleteEvento(@PathVariable int idEvento) {
        eventoService.deletarEvento(idEvento);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{idEvento}")
    public ResponseEntity atualizarEvento(
            @PathVariable Integer idEvento,
            @RequestBody @Valid Evento evento) {
        return ResponseEntity.status(200).body(eventoService.atualizarEvento(idEvento, evento));
    }

    @GetMapping("/protocolos/{idProtocolo}")
    public ResponseEntity getEventoPorProtocolo(@PathVariable String idProtocolo){

        return ResponseEntity.status(200).body(eventoService.getEventoProtocolo(idProtocolo));
    }

    @GetMapping("/protocolos")
    public ResponseEntity getEventosProtocolo(){

        return ResponseEntity.status(200).body(eventoService.getEventosProtocolo());
    }

    @PutMapping
    public ResponseEntity putDesfazer(){
        eventoService.desfazerEvento();

        return ResponseEntity.status(200).build();
    }

    @GetMapping("/com-sem-cupom")
    public ResponseEntity getSemCupom(){
        return ResponseEntity.status(200).body(eventoService.getEventosSemCupom());
    }

}
