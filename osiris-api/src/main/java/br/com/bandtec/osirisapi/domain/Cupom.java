package br.com.bandtec.osirisapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
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
    @Future
    private LocalDateTime dataValidado;

    @NotNull
    private Boolean cupomEcommerce;

    @NotNull
    @Positive
    private Integer idConsumidorEcommerce;

    @NotNull
    private Boolean usado;

    @NotNull
    @ManyToOne
    private Ecommerce ecommerce;
}
