package br.com.bandtec.calculometricas.model.dao;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompra;
    private Integer idConsumidorEcommerce;
    private String nomeProduto;
    private Double preco;
    private String nomeCategoria;

    @Temporal(value = TemporalType.DATE)
    private Date dataCompra;

    private String cupom;
    private Integer fkEcommerce;
    private Integer fkCupom;
    private Integer fkStatus;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdConsumidorEcommerce() {
        return idConsumidorEcommerce;
    }

    public void setIdConsumidorEcommerce(Integer idConsumidorEcommerce) {
        this.idConsumidorEcommerce = idConsumidorEcommerce;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getCupom() {
        return cupom;
    }

    public void setCupom(String cupom) {
        this.cupom = cupom;
    }

    public Integer getFkEcommerce() {
        return fkEcommerce;
    }

    public void setFkEcommerce(Integer fkEcommerce) {
        this.fkEcommerce = fkEcommerce;
    }

    public Integer getFkCupom() {
        return fkCupom;
    }

    public void setFkCupom(Integer fkCupom) {
        this.fkCupom = fkCupom;
    }

    public Integer getFkStatus() {
        return fkStatus;
    }

    public void setFkStatus(Integer fkStatus) {
        this.fkStatus = fkStatus;
    }
}
