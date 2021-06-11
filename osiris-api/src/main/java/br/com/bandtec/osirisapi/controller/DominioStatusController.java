package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.DominioStatus;
import br.com.bandtec.osirisapi.service.DominioStatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/dominios")
@AllArgsConstructor
public class DominioStatusController {

    DominioStatusService dominioStatusService;

    @GetMapping
    public ResponseEntity getDominioStatus() {

        return ResponseEntity.status(200).body(dominioStatusService.getAllDominios());
    }

    @PostMapping
    public ResponseEntity postDominioStatus(@RequestBody @Valid DominioStatus dominioStatus){
        dominioStatusService.saveDominio(dominioStatus);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{idDominioStatus}")
    public ResponseEntity deleteDominioStatus(@PathVariable int idDominioStatus) {
        dominioStatusService.deleteDomino(idDominioStatus);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{idDominioStatus}")
    public ResponseEntity atualizarDominioStatus(
            @PathVariable Integer idDominioStatus,
            @RequestBody DominioStatus dominioStatus) {
        dominioStatusService.atualizarDominioPeloId(idDominioStatus, dominioStatus);
        return ResponseEntity.status(200).build();
    }

}
