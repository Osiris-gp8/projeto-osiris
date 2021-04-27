package br.com.bandtec.osirisapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMeta;

    @NotNull
    @PastOrPresent
    private LocalDateTime dataInicio;

    @NotNull
    @PastOrPresent
    private LocalDateTime dataFim;

    @NotNull
    @Positive
    private Double valor;

    @NotNull
    @Positive
    private Integer fkEcommerce;

}