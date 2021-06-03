package br.com.bandtec.osirisapi.projetoUpload;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeArquivo;

    // o tipo byte[] é usado para guardar arquivos no banco
    // é recomendável definir o tamanho de campos byte[] (esse tamanho é em bytes)
    // pois alguns bancos usam um tamanho muito pequeno como padrão
    @Column(length = 5_000_000)
    private byte[] conteudoArquivo;

    private String tipoArquivo;

}

