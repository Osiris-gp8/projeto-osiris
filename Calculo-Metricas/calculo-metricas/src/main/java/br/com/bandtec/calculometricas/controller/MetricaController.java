package br.com.bandtec.calculometricas.controller;
import br.com.bandtec.calculometricas.domain.Cupom;
import br.com.bandtec.calculometricas.domain.Evento;
import br.com.bandtec.calculometricas.repository.CupomRepository;
import br.com.bandtec.calculometricas.views.CupomMaisUsado;
import br.com.bandtec.calculometricas.repository.AcessoRepository;
import br.com.bandtec.calculometricas.repository.EventoRepository;
import br.com.bandtec.calculometricas.views.RanqueCategoria;
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

    private final AcessoRepository ar;
    private final EventoRepository er;
    private final CupomRepository cr;

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

    @GetMapping("/ranqueCategoria")
    public ResponseEntity getRanqueCategoria() {
        List<RanqueCategoria> ranque = er.ranque();
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
        List<Evento> compras = er.findAllByIdConsumidorEcommerce(idConsumidorEcommerce);
        if (compras.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(compras);
    }

    @GetMapping("/listaCupomExpirado")
    public ResponseEntity getListaCupomExpirado(){
        List<Cupom> lista = cr.findAllByUsadoIsFalseAndDataValidadoLessThanTodayNow();
        if (lista.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/contagemCupomExpirado")
    public ResponseEntity getContagemCupomExpirado(){
        Integer total = cr.countAllByUsadoIsFalseAndDataValidadoLessThanTodayNow();
        if (total == null) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(total);
    }

    @GetMapping("/listaCompraCupomNaoUsado")
    public ResponseEntity getListaCompraCupomNaoUsado(){
        List<Evento> lista = er.findAllByCupomAndEventoAndUsadoIsFalseAndFkStatus();
        if (lista.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/contagemCompraCupomNaoUsado")
    public ResponseEntity getContagemCompraCupomNaoUsado(){
        Integer total = er.countAllByCupomAndEventoAndUsadoIsFalseAndFkStatus();
        if (total == null) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(total);
    }

}
