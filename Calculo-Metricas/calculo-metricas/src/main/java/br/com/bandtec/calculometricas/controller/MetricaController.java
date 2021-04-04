package br.com.bandtec.calculometricas.controller;

import br.com.bandtec.calculometricas.model.Evento;
import br.com.bandtec.calculometricas.repository.AcessosRepository;
import br.com.bandtec.calculometricas.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/metricas")
public class MetricaController {

    @Autowired
    AcessosRepository ar;

    @Autowired
    EventoRepository er;

    @GetMapping("/ultimaSemana")
    public ResponseEntity getUltimaSemana() {
        Integer count = ar.countAcessosSemana();
        if (count==0) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(count);
    }

    @GetMapping("/vendasAcesso")
    public ResponseEntity getVendasAcesso() {
        Double media = (double) ar.count() / er.count();
        if (media==0) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(media);
    }

    @GetMapping("/ranque")
    public ResponseEntity getRanque() {
        List<String> ranque = er.ranque();
        if (ranque.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(ranque);
    }


    @GetMapping("/{idConsumidorEcommerce}")
    public ResponseEntity getComprasProduto(@PathVariable Integer idConsumidorEcommerce){
        List<Evento> teste = er.findAllByIdConsumidorEcommerce(idConsumidorEcommerce);
        if (teste.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(teste);
    }

}
