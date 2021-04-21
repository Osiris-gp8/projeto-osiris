package br.com.bandtec.osirisapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompra;
    private Integer idConsumidorEcommerce;
    private String nomeProduto;
    private Double preco;
    private String nomeCategoria;
    private LocalDateTime dataCompra;
    private String cupom;
    private Integer fkEcommerce;
    private Integer fkCupom;
    private Integer fkStatus;

}
