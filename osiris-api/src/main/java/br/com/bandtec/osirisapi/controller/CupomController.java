package br.com.bandtec.osirisapi.controller;
import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.repository.CupomRepository;
import br.com.bandtec.osirisapi.service.CupomService;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cupons")
@AllArgsConstructor
public class CupomController {

    private final CupomRepository cupomRepository;
    private final CupomService cupomService;

    @GetMapping
    public ResponseEntity getCupom() {
        List<Cupom> cupons = cupomRepository.findAll();
        if (cupons.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(cupons);
        }
    }

    @PostMapping
    public ResponseEntity postCupom(@RequestBody Cupom novoCupom) {
        cupomRepository.save(novoCupom);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCupom(@PathVariable int idCupom) {
        if (cupomRepository.existsById(idCupom)) {
            cupomRepository.deleteById(idCupom);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{idCupom}")
    public ResponseEntity putCupom(
            @PathVariable Integer idCupom,
            @RequestBody Cupom cupomAtualizar){

        try {
            return ResponseEntity.status(200).body(cupomService.atualizarCupom(idCupom, cupomAtualizar));
        } catch (NotFoundException e){
            return ResponseEntity.status(404).build();
        }
    }
}
