package br.com.bandtec.osirisapi.domain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cupom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCupom;

    @NotBlank
    private String nomeCupom;

    @NotNull
    @Positive
    private Double valor;

    @NotNull
    @PastOrPresent
    private LocalDateTime dataEmitido;

    @NotNull
    @PastOrPresent
    private LocalDateTime dataValidado;

    @NotNull
    private Boolean cupomEcommerce;

    @NotNull
    @Positive
    private Integer idConsumidorEcommerce;

    @NotNull
    private Boolean usado;
}