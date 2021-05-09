package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.repository.AcessoRepository;
import br.com.bandtec.osirisapi.repository.CupomRepository;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import br.com.bandtec.osirisapi.service.MetricaService;
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
    private final MetricaService metricaService;

    @GetMapping("/ultima-semana")
    public ResponseEntity getUltimaSemana() {
        Integer acessosUltimaSemana = metricaService.getUltimaSemana();
        if (acessosUltimaSemana.equals(0)){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(acessosUltimaSemana);
    }

    @GetMapping("/vendas-acesso")
    public ResponseEntity getVendasAcesso() {

        return ResponseEntity.status(200).body(metricaService.getVendasPorAcesso());
    }

    @GetMapping("/ranque-categoria")
    public ResponseEntity getRanqueCategoria() {

        return ResponseEntity.status(200).body(metricaService.getRanqueCategoriaView());
    }

    @GetMapping("/mais-usado")
    public ResponseEntity getCupomMaisUsado(){

        return ResponseEntity.status(200).body(metricaService.getCupomMaisUsadoView());
    }

    @GetMapping("/{idConsumidorEcommerce}")
    public ResponseEntity getComprasProduto(@PathVariable Integer idConsumidorEcommerce){

        return ResponseEntity.status(200).body(metricaService.getComprasPorConsumidor(idConsumidorEcommerce));
    }

    @GetMapping("/cupons-expirado")
    public ResponseEntity getListaCupomExpirado(){

        return ResponseEntity.status(200).body(metricaService.getCuponsExpirados());
    }

    @GetMapping("/compras-cupom-nao-usado")
    public ResponseEntity getListaCompraCupomNaoUsado(){

        return ResponseEntity.status(200).body(metricaService.getComprasSemCupom());
    }


}
