package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.domain.DominioStatus;
import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.dto.request.ExportacaoRequest;
import br.com.bandtec.osirisapi.layout.LayoutEvento;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import br.com.bandtec.osirisapi.service.ArquivoService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/arquivos")
@AllArgsConstructor
public class ArquivoController {

    private final ArquivoService arquivoService;
    private final EventoRepository eventoRepository;

    @GetMapping(value = "/relatorio-csv", produces = "text/csv")
    @ResponseBody
    public ResponseEntity downloadCsv() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=exportacao.csv");

        return ResponseEntity.status(200).headers(headers).body(arquivoService.gerarCsv());
    }

    @GetMapping(value = "/relatorio-txt", produces = "text/plain")
    @ResponseBody
    public ResponseEntity downloadTxt(@Valid ExportacaoRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=exportacao.txt");

        return ResponseEntity.status(200).headers(headers).body(arquivoService.gerarTxt(request));
    }

    @PostMapping("/importacao-txt")
    public ResponseEntity importarTXT(@RequestParam MultipartFile arquivo){

        try {
            InputStream inputStream = arquivo.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedInputStream = new BufferedReader(inputStreamReader);


            arquivoService.importarTXT(bufferedInputStream);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }


        return ResponseEntity.status(201).build();
    }

}
