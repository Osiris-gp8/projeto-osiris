package br.com.bandtec.osirisapi.projetoUpload;

import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.repository.EventoRepository;
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

    private EventoRepository eventoRepository;

    private Integer idEvento;
    private Integer idConsumidor;
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

    @PostMapping("/importacaoCSV")
    public ResponseEntity criarImportacaoCSV(@RequestParam MultipartFile arquivo) throws IOException {
        Evento evento = new Evento();
        Cupom cupom = new Cupom();
        Ecommerce ecommerce = new Ecommerce();

        // criar objeto de Ecommerce e fazer nomeEcommerce

        String conteudoString = new String(arquivo.getBytes());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String[] conteudoSeparado = conteudoString.split(";");
        Scanner entradaCSV = null;

        idEvento = Integer.valueOf(conteudoSeparado[0]);
        idConsumidor = Integer.valueOf(conteudoSeparado[1]);
        nomeProduto = conteudoSeparado[2];
        precoProduto = Double.valueOf(conteudoSeparado[3]);
        categoriaProduto = conteudoSeparado[4];
        dataCompra = LocalDateTime.parse(conteudoSeparado[5]);
        nomeEcommerce = conteudoSeparado[6];
        nomeCupom = conteudoSeparado[7];
        valorCupom = Double.valueOf(conteudoSeparado[8]);

        evento.setIdEvento(idEvento);
        evento.setIdConsumidorEcommerce(idConsumidor);
        evento.setNomeProduto(nomeProduto);
        evento.setPreco(precoProduto);
        evento.setNomeCategoria(categoriaProduto);
        evento.setDataCompra(dataCompra);
        ecommerce.setNome(nomeEcommerce);
        cupom.setNomeCupom(nomeCupom);
        cupom.setValor(valorCupom);
        evento.setCupom(cupom);

        eventoRepository.save(evento);

        return ResponseEntity.status(201).build();

    }


    @PostMapping("/importacaoTXT")
    public ResponseEntity criarImportacaoTXT(@RequestParam MultipartFile arquivo) throws IOException {
        Evento evento = new Evento();
        Ecommerce ecommerce = new Ecommerce();
        Cupom cupom = new Cupom();
        String conteudoString = new String(arquivo.getBytes());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String[] conteudoSeparado = conteudoString.split(";");


       // tipoRegistro = registro.substring(0, 2); // obtém os 2 primeiros caracteres do registro

      //  if (tipoRegistro.equals("00")) {
       //     System.out.println("Header");
         //   System.out.println("Tipo de arquivo: " + registro.substring(2, 6));
           // int periodoLetivo= Integer.parseInt(registro.substring(6,11));
          //  System.out.println("Período letivo: " + periodoLetivo);
          //  System.out.println("Data/hora de geração do arquivo: " + registro.substring(11,30));
          //  System.out.println("Versão do layout: " + registro.substring(30,32));
      //  }
      //  else if (tipoRegistro.equals("01")) {
          //  System.out.println("\nTrailer");
          //  int qtdRegistro = Integer.parseInt(registro.substring(2,12));
        //    if (qtdRegistro == contRegistro) {
            //    System.out.println("Quantidade de registros gravados compatível com quantidade lida");
        //    }
        //    else {
        //        System.out.println("Quantidade de registros gravados não confere com quantidade lida");
         //   }
      //  }
      //  else if (tipoRegistro.equals("02")) {
       //     if (contRegistro == 0) {
        //        System.out.println();
       //         System.out.printf("%-8s %-50s %-40s %5s %6s\n","RA","NOME DO ALUNO","DISCIPLINA",
       //                 "MÉDIA", "FALTAS");

        //    }
        idEvento = Integer.valueOf(conteudoSeparado[0].substring(3,7));
        idConsumidor = Integer.valueOf(conteudoSeparado[1].substring(8,12));
        nomeProduto = conteudoSeparado[2].substring(13,57);
        precoProduto = Double.valueOf(conteudoSeparado[3].substring(58,66).replace(',','.'));
        categoriaProduto = conteudoSeparado[4].substring(67,111);
        dataCompra = LocalDateTime.parse(conteudoSeparado[5].substring(111,130));
        nomeEcommerce = conteudoSeparado[6].substring(131,175);
        nomeCupom = conteudoSeparado[7].substring(176,220);
        valorCupom = Double.valueOf(conteudoSeparado[8].substring(221,227).replace(',','.'));

        evento.setIdEvento(idEvento);
        evento.setIdConsumidorEcommerce(idConsumidor);
        evento.setNomeProduto(nomeProduto);
        evento.setPreco(precoProduto);
        evento.setNomeCategoria(categoriaProduto);
        evento.setDataCompra(dataCompra);
        ecommerce.setNome(nomeEcommerce);
        cupom.setNomeCupom(nomeCupom);
        cupom.setValor(valorCupom);
        evento.setCupom(cupom);

        //    contRegistro++;
      //  }


           eventoRepository.save(evento);
        return ResponseEntity.status(201).build();
    }

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
