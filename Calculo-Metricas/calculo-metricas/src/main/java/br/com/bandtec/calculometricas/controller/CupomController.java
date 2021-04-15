package br.com.bandtec.calculometricas.controller;
import br.com.bandtec.calculometricas.model.Cupom;
import br.com.bandtec.calculometricas.repository.CupomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cupons")
@AllArgsConstructor
public class CupomController {

    private final CupomRepository cr;

    @GetMapping
    public ResponseEntity getCupom() {
        List<Cupom> cupons = cr.findAll();
        if (cupons.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(cupons);
        }
    }

    @PostMapping
    public ResponseEntity postCupom(@RequestBody Cupom novoCupom) {
        cr.save(novoCupom);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCupom(@PathVariable int idCupom) {
        if (cr.existsById(idCupom)) {
            cr.deleteById(idCupom);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
