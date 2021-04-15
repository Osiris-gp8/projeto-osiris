package br.com.bandtec.calculometricas.controller;

import br.com.bandtec.calculometricas.model.Acessos;
import br.com.bandtec.calculometricas.repository.AcessosRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acessos")
@AllArgsConstructor
public class AcessosController {

    private final AcessosRepository ar;

    @GetMapping
    public ResponseEntity getAcessos() {
        List<Acessos> acessos = ar.findAll();
        if (acessos.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(acessos);
        }
    }

    @PostMapping
    public ResponseEntity postAcesso(@RequestBody Acessos novoAcesso) {
        ar.save(novoAcesso);
        return ResponseEntity.status(201).build();
    }

}
