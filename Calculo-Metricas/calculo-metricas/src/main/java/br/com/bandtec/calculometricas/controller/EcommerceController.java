package br.com.bandtec.calculometricas.controller;
import br.com.bandtec.calculometricas.model.Ecommerce;
import br.com.bandtec.calculometricas.repository.EcommerceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecommerces")
public class EcommerceController {

    @Autowired
    EcommerceRepository ecr;

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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEcommerce(@PathVariable int idEcommerce) {
        if (ecr.existsById(idEcommerce)) {
            ecr.deleteById(idEcommerce);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
