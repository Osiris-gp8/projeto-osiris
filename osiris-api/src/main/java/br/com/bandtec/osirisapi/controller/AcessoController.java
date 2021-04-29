package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Acesso;
import br.com.bandtec.osirisapi.repository.AcessoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acessos")
@AllArgsConstructor
public class AcessoController {

    private final AcessoRepository acessoRepository;

    @GetMapping
    public ResponseEntity getAcessos() {
        List<Acesso> acessos = acessoRepository.findAll();
        if (acessos.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(acessos);
        }
    }

    @PostMapping
    public ResponseEntity postAcesso(@RequestBody Acesso novoAcesso) {
        acessoRepository.save(novoAcesso);
        return ResponseEntity.status(201).build();
    }

}
