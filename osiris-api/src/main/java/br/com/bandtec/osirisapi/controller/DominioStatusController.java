package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.DominioStatus;
import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.repository.DominioStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.ResponseMessage;

import java.util.List;

@RestController
@RequestMapping("/dominios")
@AllArgsConstructor
public class DominioStatusController {

    private final DominioStatusRepository dsr;

    @GetMapping
    public ResponseEntity getDominioStatus() {
        List<DominioStatus> dominios = dsr.findAll();
        if (dominios.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(dominios);
        }
    }

    @PostMapping
    public ResponseEntity postDominioStatus(@RequestBody DominioStatus dominioStatus){
        dsr.save(dominioStatus);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDominioStatus(@PathVariable int idDominioStatus) {
        if (dsr.existsById(idDominioStatus)) {
            dsr.deleteById(idDominioStatus);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{idEcommerce}")
    public ResponseEntity atualizarDominioStatus(@RequestBody DominioStatus dominioStatus){
        if (dsr.findById(dominioStatus.getIdDominioStatus()).isPresent()){
            dsr.save(dominioStatus);
            return ResponseEntity.status(200).build();
        }else {
            return ResponseEntity.status(404).build();
        }
    }

}
