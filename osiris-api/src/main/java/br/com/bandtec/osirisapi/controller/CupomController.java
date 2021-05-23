package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.repository.CupomRepository;
import br.com.bandtec.osirisapi.service.CupomService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cupons")
@AllArgsConstructor
public class CupomController {

    private final CupomService cupomService;

    @GetMapping
    public ResponseEntity getCupons() {

        return ResponseEntity.status(200).body(cupomService.buscarCupons());

    }

    @GetMapping("/{idCupom}")
    public ResponseEntity getCupom(@PathVariable Integer idCupom) {

        return ResponseEntity.status(200).body(cupomService.buscarCupom(idCupom));
    }

    @PostMapping
    public ResponseEntity postCupom(@RequestBody @Valid Cupom novoCupom) {
        cupomService.inserirCupom(novoCupom);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/list")
    public ResponseEntity postCupons(@RequestBody List<Cupom> cupons){

        return ResponseEntity.status(201).body(cupomService.adicionarCupons(cupons));
    }

    @DeleteMapping("/{idCupom}")
    public ResponseEntity deleteCupom(@PathVariable int idCupom) {

        cupomService.deleteCupom(idCupom);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{idCupom}")
    public ResponseEntity putCupom(
            @PathVariable Integer idCupom,
            @RequestBody @Valid Cupom cupomAtualizar) {

            return ResponseEntity.status(200).body(cupomService.atualizarCupom(idCupom, cupomAtualizar));

    }
}
