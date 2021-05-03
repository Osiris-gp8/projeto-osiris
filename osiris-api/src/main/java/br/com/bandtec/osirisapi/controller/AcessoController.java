package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Acesso;
import br.com.bandtec.osirisapi.service.AcessoService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acessos")
@AllArgsConstructor
public class AcessoController {

    private final AcessoService acessoService;

    @GetMapping
    public ResponseEntity getAcessos() throws NotFoundException {
        return ResponseEntity.status(200).body(acessoService.getAcessos());
    }

    @PostMapping
    public ResponseEntity postAcesso(@RequestBody Acesso novoAcesso) {
        acessoService.inserirAcesso(novoAcesso);
        return ResponseEntity.status(201).build();
    }

}
