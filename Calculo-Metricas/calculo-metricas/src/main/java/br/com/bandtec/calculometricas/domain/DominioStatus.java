package br.com.bandtec.calculometricas.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DominioStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDominioStatus;

    private String nome;

}
