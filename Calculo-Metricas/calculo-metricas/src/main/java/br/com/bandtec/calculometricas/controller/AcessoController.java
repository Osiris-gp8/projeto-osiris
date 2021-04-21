package br.com.bandtec.calculometricas.controller;

import br.com.bandtec.calculometricas.domain.Acesso;
import br.com.bandtec.calculometricas.repository.AcessoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acessos")
@AllArgsConstructor
public class AcessoController {

    private final AcessoRepository ar;

    @GetMapping
    public ResponseEntity getAcessos() {
        List<Acesso> acessos = ar.findAll();
        if (acessos.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(acessos);
        }
    }

    @PostMapping
    public ResponseEntity postAcesso(@RequestBody Acesso novoAcesso) {
        ar.save(novoAcesso);
        return ResponseEntity.status(201).build();
    }

}
