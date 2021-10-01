package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.service.EcommerceService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity postEcommerce(@RequestBody @Valid Ecommerce novoEcommerce) {
        return ResponseEntity.status(201).body(ecommerceService.inserirEcommerce(novoEcommerce));
    }

    @DeleteMapping("/{idEcommerce}")
    public ResponseEntity deleteEcommerce(@PathVariable int idEcommerce) {
       ecommerceService.deletarEcommerce(idEcommerce);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/{idEcommerce}")
    public ResponseEntity atualizarEcommerce(
            @PathVariable Integer idEcommerce,
            @RequestBody @Valid Ecommerce ecommerce) {
        return ResponseEntity.status(200).body(ecommerceService.atualizarEcommerce(idEcommerce, ecommerce));
    }

    @GetMapping("/id")
    public ResponseEntity getIdEcommercePeloNomeECnpj(@RequestParam String cnpj,
                                                    @RequestParam String nomeEcommerce) {;
        return ResponseEntity.status(200).body(ecommerceService.getIdEcommercePeloCnpjENome(cnpj, nomeEcommerce));
    }
}
