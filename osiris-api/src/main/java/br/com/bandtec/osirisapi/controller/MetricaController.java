package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.repository.AcessoRepository;
import br.com.bandtec.osirisapi.repository.CupomRepository;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import br.com.bandtec.osirisapi.views.CupomMaisUsadoView;
import br.com.bandtec.osirisapi.views.RanqueCategoriaView;
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

    private final AcessoRepository acessoRepository;
    private final EventoRepository eventoRepository;
    private final CupomRepository cupomRepository;

    @GetMapping("/ultimaSemana")
    public ResponseEntity getUltimaSemana() {
        Integer count = acessoRepository.countAcessosSemana();
        if (count==0) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(count);
    }

    @GetMapping("/vendasAcesso")
    public ResponseEntity getVendasAcesso() {
        Double media = (double) acessoRepository.count() / eventoRepository.count();
        if (media==0) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(media);
    }

    @GetMapping("/ranqueCategoria")
    public ResponseEntity getRanqueCategoria() {
        List<RanqueCategoriaView> ranque = eventoRepository.ranque();
        if (ranque.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(ranque);
    }

    @GetMapping("/maisUsado")
    public ResponseEntity getCupomMaisUsado(){
        List<CupomMaisUsadoView> cupomMaisUsadoView = eventoRepository.cupomMaisUsado();
        if (cupomMaisUsadoView.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(cupomMaisUsadoView);
    }

    @GetMapping("/{idConsumidorEcommerce}")
    public ResponseEntity getComprasProduto(@PathVariable Integer idConsumidorEcommerce){
        List<Evento> compras = eventoRepository.findAllByIdConsumidorEcommerce(idConsumidorEcommerce);
        if (compras.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(compras);
    }

    @GetMapping("/listaCupomExpirado")
    public ResponseEntity getListaCupomExpirado(){
        List<Cupom> lista = cupomRepository.findAllByUsadoIsFalseAndDataValidadoLessThanTodayNow();
        if (lista.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/contagemCupomExpirado")
    public ResponseEntity getContagemCupomExpirado(){
        Integer total = cupomRepository.countAllByUsadoIsFalseAndDataValidadoLessThanTodayNow();
        if (total == null) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(total);
    }

    @GetMapping("/listaCompraCupomNaoUsado")
    public ResponseEntity getListaCompraCupomNaoUsado(){
        List<Evento> lista = eventoRepository.findAllByCupomAndEventoAndUsadoIsFalseAndFkStatus();
        if (lista.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/contagemCompraCupomNaoUsado")
    public ResponseEntity getContagemCompraCupomNaoUsado(){
        Integer total = eventoRepository.countAllByCupomAndEventoAndUsadoIsFalseAndFkStatus();
        if (total == null) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(total);
    }

}
