package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Meta;
import br.com.bandtec.osirisapi.repository.MetaRepository;
import br.com.bandtec.osirisapi.service.MetaService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/metas")
@AllArgsConstructor
public class MetaController {

    private MetaService metaService;

    @GetMapping
    public ResponseEntity getMetas() {
        return ResponseEntity.status(200).body(metaService.getMetas());
    }

    @PostMapping
    public ResponseEntity postMeta(@RequestBody @Valid final Meta novaMeta) {
        return ResponseEntity.status(201).body(metaService.inserirMeta(novaMeta));
    }

    @PutMapping("/{idMeta}")
    public ResponseEntity putMeta(
            @PathVariable Integer idMeta,
            @RequestBody Meta meta) {
        return ResponseEntity.status(200).body(metaService.atualizarMeta(idMeta, meta));
    }

    @DeleteMapping("/{idMeta}")
    public ResponseEntity deleteMeta(@PathVariable Integer idMeta) {
        metaService.deletarMeta(idMeta);
        return ResponseEntity.status(200).build();
    }
}
