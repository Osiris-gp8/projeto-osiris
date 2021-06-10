package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.service.EventoService;
import br.com.bandtec.osirisapi.service.TokenService;
import com.sun.corba.se.impl.interceptors.RequestInfoImpl;
import lombok.AllArgsConstructor;
import org.omg.PortableInterceptor.RequestInfo;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public ResponseEntity postEvento(@RequestBody @Valid Evento novoEvento) {

        return ResponseEntity.status(201).body(eventoService.inserirEvento(novoEvento));
    }

    @DeleteMapping("/{idEvento}")
    public ResponseEntity deleteEvento(@PathVariable int idEvento, HttpServletRequest httpRequest) {
        eventoService.deletarEvento(idEvento, httpRequest);
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
