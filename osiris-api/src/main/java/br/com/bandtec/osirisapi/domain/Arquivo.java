package br.com.bandtec.osirisapi.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeArquivo;

    private Long tamanhoBytes;

    private String status;

    @ManyToOne
    @JoinColumn(name = "ecommerce_id_ecommerce")
    private Ecommerce ecommerce;
}
