package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.converter.ArquivoConverter;
import br.com.bandtec.osirisapi.converter.S3converter;
import br.com.bandtec.osirisapi.domain.Arquivo;
import br.com.bandtec.osirisapi.dto.request.ExportacaoRequest;
import br.com.bandtec.osirisapi.dto.request.FileS3Request;
import br.com.bandtec.osirisapi.dto.response.ContagemArquivosComErroResponse;
import br.com.bandtec.osirisapi.dto.response.S3ArquivoDownloadResponse;
import br.com.bandtec.osirisapi.dto.response.TamanhoArquivoBytesResponse;
import br.com.bandtec.osirisapi.repository.ArquivoRepository;
import br.com.bandtec.osirisapi.service.ArquivoService;
import br.com.bandtec.osirisapi.utils.ArquivoStatusConstants;
import br.com.bandtec.osirisapi.utils.hashing.HashTable;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@RestController
@RequestMapping("/arquivos")
@AllArgsConstructor
public class ArquivoController {

    private final ArquivoService arquivoService;
    private final S3converter s3converter;
    private final HashTable hashTable;
    private final ArquivoConverter arquivoConverter;
    private final ArquivoStatusConstants arquivoStatusConstants;
    private final ArquivoRepository arquivoRepository;

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

        Arquivo arquivoEntity = arquivoRepository.save(arquivoConverter.multipartFileToArquivo(arquivo));

        try {
            InputStream inputStream = arquivo.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedInputStream = new BufferedReader(inputStreamReader);

            arquivoService.importarTXT(bufferedInputStream);
            arquivoEntity.setStatus(arquivoStatusConstants.STATUS_SUCESSO);

        } catch (IOException e) {
            arquivoEntity.setStatus(arquivoStatusConstants.STATUS_ERRO);
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        } catch (StringIndexOutOfBoundsException e){
            arquivoEntity.setStatus(arquivoStatusConstants.STATUS_ERRO);
            return ResponseEntity.status(400).body("Layout inválido");
        }finally {
            arquivoRepository.saveAndFlush(arquivoEntity);
        }


        hashTable.insere(arquivo);

        return ResponseEntity.status(201).build();
    }

    @GetMapping("/file-s3")
    public ResponseEntity getFileFromS3(FileS3Request request){

        List<S3ArquivoDownloadResponse> s3ArquivoDownloadResponse = s3converter
                .S3ObjectsToS3ArquivoDownloadResponse(
                        arquivoService.buscarArquivoS3(hashTable.buscar(request.getData())));

        return ResponseEntity.status(200).body(s3ArquivoDownloadResponse);
    }

    @GetMapping("/com-erro/contagem")
    public ResponseEntity<ContagemArquivosComErroResponse> getArquivosComErro(){
        return ResponseEntity.status(200).body(arquivoService.buscarArquivosComErroCount());
    }

    @GetMapping("/tamanho-bytes")
    public ResponseEntity<TamanhoArquivoBytesResponse> getTamanhoBytes(){
        return ResponseEntity.status(200).body(arquivoService.buscarTamanhoBytesArquivos());
    }

}
