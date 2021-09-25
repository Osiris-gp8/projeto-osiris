package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Acesso;
import br.com.bandtec.osirisapi.service.AcessoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/acessos")
@AllArgsConstructor
public class AcessoController {

    private final AcessoService acessoService;

    @GetMapping
    public ResponseEntity getAcessos() {
        return ResponseEntity.status(200).body(acessoService.getAcessos());
    }

    @PostMapping
    public ResponseEntity postAcesso(@RequestBody @Valid Acesso novoAcesso) {
        acessoService.inserirAcesso(novoAcesso);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/list")
    public ResponseEntity postAcessoList(@RequestBody List<@Valid Acesso> acessoList){
        acessoList.forEach(acessoService::inserirAcesso);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{inicioDia}/{fimDia}")
    public ResponseEntity getAcessosDeterminadoDia(@Valid FiltroDataRequest request)
    {
        LocalDateTime inicio = request.getDataIncio().atStartOfDay();
        LocalDateTime fim = request.getDataFinal().atStartOfDay();
        return ResponseEntity.status(200).body(acessoService.countAcessoDeterminadoDia(inicio,fim));
    }

}
