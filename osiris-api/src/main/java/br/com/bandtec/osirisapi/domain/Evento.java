package br.com.bandtec.osirisapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @NotNull
    @ManyToOne
    private Ecommerce ecommerce;

    @ManyToOne
    private Cupom cupom;

    @NotNull
    @OneToOne
    private DominioStatus dominioStatus;
}