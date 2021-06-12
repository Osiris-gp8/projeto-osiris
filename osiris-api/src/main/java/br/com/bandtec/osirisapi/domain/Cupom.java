package br.com.bandtec.osirisapi.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S][XXX]")
    private LocalDateTime dataEmitido;

    @NotNull
    @Future
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S][XXX]")
    private LocalDateTime dataValidado;

    @NotNull
    private Boolean cupomEcommerce;

    @NotNull
    @Positive
    private Integer idConsumidorEcommerce;

    @NotNull
    private Boolean usado;
}
