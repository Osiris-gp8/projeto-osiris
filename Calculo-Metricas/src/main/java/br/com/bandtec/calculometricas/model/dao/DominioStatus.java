package br.com.bandtec.calculometricas.model.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DominioStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDominioStatus;
    private String nome;

    public Integer getIdDominioStatus() {
        return idDominioStatus;
    }

    public void setIdDominioStatus(Integer idDominioStatus) {
        this.idDominioStatus = idDominioStatus;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
