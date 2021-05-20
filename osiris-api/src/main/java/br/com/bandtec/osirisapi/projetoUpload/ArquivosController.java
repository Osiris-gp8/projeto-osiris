package br.com.bandtec.osirisapi.projetoUpload;

import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.domain.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

@RestController
@RequestMapping("/arquivos")
public class ArquivosController {

    @Autowired
    private AnexoRepository anexoRepository;

    private Integer IdCompra;
    private Integer IdConsumidor;
    private String nomeProduto;
    private Double precoProduto;
    private String categoriaProduto;
    private LocalDateTime dataCompra;
    private String nomeEcommerce;
    private String nomeCupom;
    private Double valorCupom;
    private String status;

    @PostMapping
    public ResponseEntity criarArquivo(@RequestParam MultipartFile arquivo) throws IOException {

        // recuperando o nome original do arquivo
        System.out.println("Recebendo um arquivo de nome: " + arquivo.getOriginalFilename());

        // recuperando o tipo do arquivo
        System.out.println("Recebendo um arquivo do tipo: " + arquivo.getContentType());

        // recuperando o conteúdo do arquivo
        byte[] conteudo = arquivo.getBytes();
        //String conteudoString = new String(arquivo.getBytes());

        // aqui estamos gravando o arquivo na pasta de trabalho da API
        Path path = Paths.get(arquivo.getOriginalFilename());
        Files.write(path, conteudo);

        return ResponseEntity.status(201).build();
    }

//    @PostMapping("/importacaoCSV")
//    public ResponseEntity criarImportacao(@RequestParam MultipartFile arquivo) throws IOException {
//        Evento evento = new Evento();
//        Cupom cupom = new Cupom();
//        String conteudoString = new String(arquivo.getBytes());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        String[] conteudoSeparado = conteudoString.split(";");
//        Scanner entradaCSV = null;
//
//        System.out.println("CSV");
//        System.out.printf("%05d%05d%-45s%9.2f%-45s%-19s%-45s%-45s%7.2f%-45s%n\n", "IdCompra", "IdConsumidor"
//                , "NomeProduto", "PrecoProduto", "CategoriaProduto", "DataCompra", "NomeEcommerce", "NomeCupom",
//                "ValorCupom");
//        while (entradaCSV.hasNext()) {
//            IdCompra = Integer.valueOf(conteudoSeparado[0]);
//            IdConsumidor = Integer.valueOf(conteudoSeparado[1]);
//            nomeProduto = conteudoSeparado[2];
//            precoProduto = Double.valueOf(conteudoSeparado[3]);
//            categoriaProduto = conteudoSeparado[4];
//            dataCompra = LocalDateTime.parse(conteudoSeparado[5]);
//            nomeEcommerce = conteudoSeparado[6];
//            nomeCupom = conteudoSeparado[7];
//            valorCupom = Double.valueOf(conteudoSeparado[8]);
//        }
//        // evento.setIdCompra(IdCompra);
//        evento.setIdConsumidorEcommerce(IdConsumidor);
//        evento.setNomeProduto(nomeProduto);
//        evento.setPreco(precoProduto);
//        evento.setNomeCategoria(categoriaProduto);
//        evento.setDataCompra(dataCompra);
//        // evento.setFkEcommerce(nomeEcommerce);
//        cupom.setNomeCupom(nomeCupom);
//        cupom.setValor(valorCupom);
//        evento.setCupom(cupom);
//        // // ///////////////////////// CSV
//    }
//
//
//    @PostMapping("/importacaoTXT")
//    public ResponseEntity criarImportacao(@RequestParam MultipartFile arquivo) throws IOException {
//        Scanner entradaTXT = null;
//        Evento evento = new Evento();
//        Cupom cupom = new Cupom();
//        String conteudoString = new String(arquivo.getBytes());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        String[] conteudoSeparado = conteudoString.split(";");
//
//
//        System.out.println("TXT");
//        System.out.printf("%05d%05d%-45s%9.2f%-45s%-19s%-45s%-45s%7.2f%-45s%n\n", "IdCompra", "IdConsumidor"
//                , "NomeProduto", "PrecoProduto", "CategoriaProduto", "DataCompra", "NomeEcommerce", "NomeCupom",
//                "ValorCupom");
//        while (entradaTXT.hasNext()) {
//            IdCompra = Integer.valueOf(conteudoSeparado[0]);
//            IdConsumidor = Integer.valueOf(conteudoSeparado[1]);
//            nomeProduto = conteudoSeparado[2];
//            precoProduto = Double.valueOf(conteudoSeparado[3]);
//            categoriaProduto = conteudoSeparado[4];
//            dataCompra = LocalDateTime.parse(conteudoSeparado[5]);
//            nomeEcommerce = conteudoSeparado[6];
//            nomeCupom = conteudoSeparado[7];
//            valorCupom = Double.valueOf(conteudoSeparado[8]);
//
//        }
//        evento.setIdConsumidorEcommerce(IdConsumidor);
//        evento.setNomeProduto(nomeProduto);
//        evento.setPreco(precoProduto);
//        evento.setNomeCategoria(categoriaProduto);
//        evento.setDataCompra(dataCompra);
//        // evento.setFkEcommerce(nomeEcommerce);
//        cupom.setNomeCupom(nomeCupom);
//        cupom.setValor(valorCupom);
//        evento.setCupom(cupom);
//
//        //   anexoRepository.save(evento);
//    }

    @PostMapping("/anexo")
    public ResponseEntity criarAnexo(@RequestParam MultipartFile arquivo) throws IOException {

        Anexo novoAnexo = new Anexo();
        novoAnexo.setNomeArquivo(arquivo.getOriginalFilename());
        novoAnexo.setConteudoArquivo(arquivo.getBytes());
        novoAnexo.setTipoArquivo(arquivo.getContentType());

        anexoRepository.save(novoAnexo);

        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getArquivo(@PathVariable int id){
        Optional<Anexo> anexoOptional = anexoRepository.findById(id);
        if(anexoOptional.isPresent())
        {
            Anexo anexo = anexoOptional.get();

            return ResponseEntity
                    .status(200)
                    .header("content-type", anexo.getTipoArquivo())
                    .header("content-disposition", "filename="+ anexo.getNomeArquivo())
                    .body(anexo.getConteudoArquivo());
        }else{
            return ResponseEntity.status(404).build();
        }
    }

}
