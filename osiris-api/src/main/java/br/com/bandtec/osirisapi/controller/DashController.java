package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.dto.request.FiltroDataRequest;
import br.com.bandtec.osirisapi.dto.response.dash.AcessosVendasDiasResponse;
import br.com.bandtec.osirisapi.service.DashService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dash")
@AllArgsConstructor
public class DashController {

    private DashService dashService;

    @GetMapping("/contagem-acessos-vendas")
    public ResponseEntity<List<AcessosVendasDiasResponse>> getAcessosVendasUltimosSeteDias(FiltroDataRequest filtroDataRequest){

        return ResponseEntity.status(200).body(dashService.buscarAcessosVendas(filtroDataRequest));
    }

}
