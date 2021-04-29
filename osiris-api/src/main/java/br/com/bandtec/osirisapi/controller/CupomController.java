package br.com.bandtec.osirisapi.controller;
import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.repository.CupomRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cupons")
@AllArgsConstructor
public class CupomController {

    private final CupomRepository cupomRepository;

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

    @PutMapping
    public ResponseEntity putCupom(@RequestBody Cupom cupom){
        if(cupomRepository.findById(cupom.getIdCupom()).isPresent()){
            cupomRepository.save(cupom);
            return ResponseEntity.status(200).build();
        }else{
            return ResponseEntity.status(404).build();
        }
    }
}
