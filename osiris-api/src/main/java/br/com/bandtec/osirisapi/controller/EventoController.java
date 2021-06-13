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
    public ResponseEntity getEventos(HttpServletRequest httpRequest) {

        return ResponseEntity.status(200).body(eventoService.getEventos(httpRequest));
    }

    @PostMapping
    public ResponseEntity postEvento(@RequestBody @Valid Evento novoEvento, HttpServletRequest httpRequest) {

        return ResponseEntity.status(202).header(
                "protocolo",
                eventoService.inserirEventoAssincrono(novoEvento, httpRequest)).build();
    }

    @PostMapping("/list")
    public ResponseEntity postEventos(@RequestBody List<@Valid Evento> eventoList){
        eventoList.forEach(eventoService::inserirEvento);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{idEvento}")
    public ResponseEntity deleteEvento(@PathVariable int idEvento, HttpServletRequest httpRequest) {
        eventoService.deletarEvento(idEvento, httpRequest);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{idEvento}")
    public ResponseEntity atualizarEvento(
            @PathVariable Integer idEvento,
            @RequestBody @Valid Evento evento) {
        return ResponseEntity.status(200).body(eventoService.atualizarEvento(idEvento, evento));
    }

    @GetMapping("/protocolos/{idProtocolo}")
    public ResponseEntity getEventoPorProtocolo(@PathVariable String idProtocolo, HttpServletRequest httpRequest){

        return ResponseEntity.status(200).body(eventoService.getEventoProtocolo(idProtocolo, httpRequest));
    }

    @GetMapping("/protocolos")
    public ResponseEntity getEventosProtocolo(HttpServletRequest httpRequest){

        return ResponseEntity.status(200).body(eventoService.getEventosProtocolo(httpRequest));
    }

    @PutMapping
    public ResponseEntity putDesfazer(){
        eventoService.desfazerEvento();

        return ResponseEntity.status(200).build();
    }

}
