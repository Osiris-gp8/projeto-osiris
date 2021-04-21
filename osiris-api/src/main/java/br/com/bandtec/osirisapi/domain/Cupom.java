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
public class Cupom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCupom;
    private String nomeCupom;
    private Double valor;
    private LocalDateTime dataEmitido;
    private LocalDateTime dataValidado;
    private Boolean cupomEcommerce;
    private Integer idConsumidorEcommerce;
    private Boolean usado;
}
