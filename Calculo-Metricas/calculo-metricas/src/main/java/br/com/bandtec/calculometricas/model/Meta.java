package br.com.bandtec.calculometricas.model;

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
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMeta;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private Double valor;

    private Integer fkEcommerce;

}
