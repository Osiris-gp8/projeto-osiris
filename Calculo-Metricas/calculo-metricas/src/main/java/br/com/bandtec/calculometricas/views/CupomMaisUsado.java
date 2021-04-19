package br.com.bandtec.calculometricas.views;
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
public class CupomMaisUsado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer quantidades;
    private Integer cupom;

}