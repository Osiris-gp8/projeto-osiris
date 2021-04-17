package br.com.bandtec.calculometricas.model;

import lombok.AllArgsConstructor;
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
public class Acessos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAcessos;

    private Integer idConsumidorEcommerce;

    private LocalDateTime inicioAcesso;

    private LocalDateTime fimAcesso;
}
