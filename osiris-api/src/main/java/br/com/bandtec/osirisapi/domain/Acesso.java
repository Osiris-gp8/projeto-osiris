package br.com.bandtec.osirisapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAcessos;

    @NotNull
    @Positive
    private Integer idConsumidorEcommerce;

    @NotNull
    @PastOrPresent
    private LocalDateTime inicioAcesso;

    @NotNull
    @PastOrPresent
    private LocalDateTime fimAcesso;

    @NotNull
    @ManyToOne
    private Ecommerce ecommerce;
}

