package br.com.bandtec.calculometricas.controller;

import br.com.bandtec.calculometricas.model.dto.Metrica;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/metricas")
public class MetricaController {

    Metrica metrica = new Metrica();

//    @GetMapping
//    public List<String> getRanque() {
//        return metrica.getRanqueCategoria();
//    }
}
