package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.dto.request.FiltroDataRequest;
import br.com.bandtec.osirisapi.dto.response.dash.AcessosVendasDiasResponse;
import br.com.bandtec.osirisapi.service.DashService;
import br.com.bandtec.osirisapi.views.CountAcessoEventos;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dash")
@AllArgsConstructor
public class DashController {

    private DashService dashService;

    @GetMapping("/contagem-acessos-vendas")
    public ResponseEntity<List<CountAcessoEventos>> getAcessosVendasUltimosSeteDias(@Valid FiltroDataRequest filtroDataRequest){
        List<CountAcessoEventos> result = dashService.countAcessoVendasBetween(filtroDataRequest);

        if (result.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

}
