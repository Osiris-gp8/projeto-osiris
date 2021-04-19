package br.com.bandtec.calculometricas.controller;
import br.com.bandtec.calculometricas.domain.DominioStatus;
import br.com.bandtec.calculometricas.repository.DominioStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
