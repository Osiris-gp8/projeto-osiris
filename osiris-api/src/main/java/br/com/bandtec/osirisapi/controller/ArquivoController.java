package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.service.ArquivoService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arquivos")
@AllArgsConstructor
public class ArquivoController {

    private final ArquivoService arquivoService;

    @GetMapping(value = "/relatorio-csv", produces = "text/csv")
    @ResponseBody
    public ResponseEntity downloadCsv() throws NotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=exportacao.csv");

        return ResponseEntity.status(200).headers(headers).body(arquivoService.gerarCsv());
    }

    @GetMapping(value = "/relatorio-txt", produces = "text/plain")
    @ResponseBody
    public ResponseEntity downloadTxt() throws NotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=exportacao.txt");

        return ResponseEntity.status(200).headers(headers).body(arquivoService.gerarTxt());
    }

}
