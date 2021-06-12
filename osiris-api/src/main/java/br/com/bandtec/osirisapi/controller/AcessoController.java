package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Acesso;
import br.com.bandtec.osirisapi.service.AcessoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/acessos")
@AllArgsConstructor
public class AcessoController {

    private final AcessoService acessoService;

    @GetMapping
    public ResponseEntity getAcessos() {
        return ResponseEntity.status(200).body(acessoService.getAcessos());
    }

    @PostMapping
    public ResponseEntity postAcesso(@RequestBody @Valid Acesso novoAcesso) {
        acessoService.inserirAcesso(novoAcesso);
        return ResponseEntity.status(201).build();
    }

}
