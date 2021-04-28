package br.com.bandtec.osirisapi.controller;
import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.repository.EcommerceRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecommerces")
@AllArgsConstructor
public class EcommerceController {

    private final EcommerceRepository ecr;

    @GetMapping
    public ResponseEntity getEcommerce() {
        List<Ecommerce> ecommerces = ecr.findAll();
        if (ecommerces.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(ecommerces);
        }
    }

    @PostMapping
    public ResponseEntity postEcommerce(@RequestBody Ecommerce novoEcommerce) {
        ecr.save(novoEcommerce);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{idEcommerce}")
    public ResponseEntity deleteEcommerce(@PathVariable int idEcommerce) {
        if (ecr.existsById(idEcommerce)) {
            ecr.deleteById(idEcommerce);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping
    public ResponseEntity atualizarEcommerce(@RequestBody Ecommerce ecommerce){
        if (ecr.findById(ecommerce.getIdEcommerce()).isPresent()){
            ecr.save(ecommerce);
            return ResponseEntity.status(200).build();
        }else {
            return ResponseEntity.status(404).build();
        }
    }
}
