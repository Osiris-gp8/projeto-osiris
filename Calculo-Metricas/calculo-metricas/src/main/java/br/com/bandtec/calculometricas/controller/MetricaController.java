package br.com.bandtec.calculometricas.controller;

<<<<<<< HEAD
import br.com.bandtec.calculometricas.model.Evento;
import br.com.bandtec.calculometricas.views.CupomMaisUsado;
=======
import br.com.bandtec.calculometricas.domain.Evento;
>>>>>>> c7192594667ae8fd86f261a9d5575537a8dab637
import br.com.bandtec.calculometricas.repository.AcessosRepository;
import br.com.bandtec.calculometricas.repository.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/metricas")
@AllArgsConstructor
public class MetricaController {

    private final AcessosRepository ar;
    private final EventoRepository er;

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

    @GetMapping("/maisUsado")
    public ResponseEntity getCupomMaisUsado(){
        List<CupomMaisUsado> cupomMaisUsado = er.cupomMaisUsado();
        if (cupomMaisUsado.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(cupomMaisUsado);
    }

    @GetMapping("/{idConsumidorEcommerce}")
    public ResponseEntity getComprasProduto(@PathVariable Integer idConsumidorEcommerce){
        List<Evento> teste = er.findAllByIdConsumidorEcommerce(idConsumidorEcommerce);
        if (teste.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(teste);
    }

}
