package br.com.bandtec.calculometricas.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Acessos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAcessos;

    @NotNull
    @Positive
    private Integer idConsumidorEcommerce;

    @NotNull
    @PastOrPresent
    private LocalDate inicioAcesso;

    @NotNull
    @PastOrPresent
    private LocalDate fimAcesso;
}

