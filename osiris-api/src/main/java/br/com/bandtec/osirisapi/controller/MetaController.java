package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Meta;
import br.com.bandtec.osirisapi.repository.MetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metas")
@AllArgsConstructor
public class MetaController {

    private final MetaRepository metaRepository;

    @GetMapping
    public ResponseEntity getMetas() {
        List<Meta> metas = metaRepository.findAll();
        if (metas.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(metas);
        }
    }

    @PostMapping
    public ResponseEntity postAcesso(@RequestBody Meta novaMeta) {
        metaRepository.save(novaMeta);
        return ResponseEntity.status(201).build();
    }
}
