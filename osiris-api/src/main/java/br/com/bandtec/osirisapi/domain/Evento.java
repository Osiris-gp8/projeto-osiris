package br.com.bandtec.osirisapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;

    @NotNull
    @Positive
    private Integer idConsumidorEcommerce;

    @NotBlank
    private String nomeProduto;

    @NotNull
    @Positive
    private Double preco;

    @NotBlank
    private String nomeCategoria;

    @NotNull
    @PastOrPresent
    private LocalDateTime dataCompra;

    @JsonIgnore
    private LocalDate dataInclusao;

    @NotNull
    @ManyToOne
    private Ecommerce ecommerce;

    @ManyToOne
    private Cupom cupom;

    @NotNull
    @OneToOne
    private DominioStatus dominioStatus;
}
