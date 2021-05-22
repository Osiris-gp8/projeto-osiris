package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.DominioStatus;
import br.com.bandtec.osirisapi.service.DominioStatusService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dominios")
@AllArgsConstructor
public class DominioStatusController {

    DominioStatusService dominioStatusService;

    @GetMapping
    public ResponseEntity getDominioStatus() throws NotFoundException {

        return ResponseEntity.status(200).body(dominioStatusService.getAllDominios());
    }

    @PostMapping
    public ResponseEntity postDominioStatus(@RequestBody DominioStatus dominioStatus){
        dominioStatusService.saveDominio(dominioStatus);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{idDominioStatus}")
    public ResponseEntity deleteDominioStatus(@PathVariable int idDominioStatus) throws NotFoundException {
        dominioStatusService.deleteDomino(idDominioStatus);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{idDominioStatus}")
    public ResponseEntity atualizarDominioStatus(
            @PathVariable Integer idDominioStatus,
            @RequestBody DominioStatus dominioStatus) throws NotFoundException {
        dominioStatusService.atualizarDominioPeloId(idDominioStatus, dominioStatus);
        return ResponseEntity.status(200).build();
    }

}
