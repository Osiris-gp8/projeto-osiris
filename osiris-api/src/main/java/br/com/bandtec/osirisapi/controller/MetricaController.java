package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.dto.barChart.EventoAcessoChartResponse;
import br.com.bandtec.osirisapi.dto.request.FiltroDataRequest;
import br.com.bandtec.osirisapi.dto.response.dash.AcessoUfResponse;
import br.com.bandtec.osirisapi.dto.response.dash.RanqueCategoriaResponse;
import br.com.bandtec.osirisapi.service.MetricaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/metricas")
@AllArgsConstructor
public class MetricaController {

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
    public ResponseEntity<List<RanqueCategoriaResponse>> getRanqueCategoria(@Valid FiltroDataRequest filtroDataRequest) {

        return ResponseEntity.status(200).body(metricaService.getRanqueCategoriaView(filtroDataRequest));
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

    @GetMapping("/dados-grafico-barra")
    public ResponseEntity getAcessosEventosUltimaSemana() {
        List<EventoAcessoChartResponse> responseList = metricaService.getAcessosEventosUltimaSemana();

        if (responseList.isEmpty()) return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(responseList);
    }

    @GetMapping("/acessos-por-uf")
    public ResponseEntity<List<AcessoUfResponse>> getAcessosPorUf(@Valid FiltroDataRequest request){
        List<AcessoUfResponse> result = metricaService.getAcessosByUf(
                request.getDataInicio().atStartOfDay(), request.getDataFinal().atStartOfDay());

        return ResponseEntity.status(200).body(result);
    }


}
