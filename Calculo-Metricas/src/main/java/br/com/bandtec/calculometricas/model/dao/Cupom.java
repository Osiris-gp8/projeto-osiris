package br.com.bandtec.calculometricas.model.dao;

import javax.persistence.*;
import java.util.Date;

@Entity

public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCupom;
    private String nomeCupom;
    private Double valor;

    @Temporal(value = TemporalType.DATE)
    private Date dataExpirado;

    @Temporal(value = TemporalType.DATE)
    private Date dataValidado;

    public Integer getIdCupom() {
        return idCupom;
    }

    public void setIdCupom(Integer idCupom) {
        this.idCupom = idCupom;
    }

    public String getNomeCupom() {
        return nomeCupom;
    }

    public void setNomeCupom(String nomeCupom) {
        this.nomeCupom = nomeCupom;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDataExpirado() {
        return dataExpirado;
    }

    public void setDataExpirado(Date dataExpirado) {
        this.dataExpirado = dataExpirado;
    }

    public Date getDataValidado() {
        return dataValidado;
    }

    public void setDataValidado(Date dataValidado) {
        this.dataValidado = dataValidado;
    }
}