package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.service.EcommerceService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecommerces")
@AllArgsConstructor
public class EcommerceController {

    private final EcommerceService ecommerceService;

    @GetMapping
    public ResponseEntity getEcommerce() {
        return ResponseEntity.status(200).body(ecommerceService.getEcommerces());
    }

    @PostMapping
    public ResponseEntity postEcommerce(@RequestBody Ecommerce novoEcommerce) {
        return ResponseEntity.status(201).body(ecommerceService.inserirEcommerce(novoEcommerce));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEcommerce(@PathVariable int idEcommerce) {
       ecommerceService.deletarEcommerce(idEcommerce);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{idEcommerce}")
    public ResponseEntity atualizarEcommerce(
            @PathVariable Integer idEcommerce,
            @RequestBody Ecommerce ecommerce) {
        return ResponseEntity.status(200).body(ecommerceService.atualizarEcommerce(idEcommerce, ecommerce));
    }
}
