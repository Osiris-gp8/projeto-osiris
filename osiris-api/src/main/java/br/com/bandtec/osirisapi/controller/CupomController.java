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
    public ResponseEntity getCupons() {

        try{
            return ResponseEntity.status(200).body(cupomService.buscarCupons());
        }catch (NotFoundException e){
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/{idCupom}")
    public ResponseEntity getCupom(@PathVariable Integer idCupom){

        try {
            return ResponseEntity.status(200).body(cupomService.buscarCupom(idCupom));
        }catch (NotFoundException e){
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping
    public ResponseEntity postCupom(@RequestBody Cupom novoCupom) {
        cupomService.inserirCupom(novoCupom);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCupom(@PathVariable int idCupom) {

        try {
            cupomService.deleteCupom(idCupom);
            return ResponseEntity.status(200).build();
        } catch (NotFoundException e){
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
