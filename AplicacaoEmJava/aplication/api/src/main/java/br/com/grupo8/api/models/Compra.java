package br.com.grupo8.api.models;

public class Compra {
    private Integer idCompra;
    private Integer pontos;
    private Double total;
    private Integer consumidor;

    public Compra(Integer idCompra, Integer pontos, Double total, Integer consumidor) {
        this.idCompra = idCompra;
        this.pontos = pontos;
        this.total = total;
        this.consumidor = consumidor;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Integer consumidor) {
        this.consumidor = consumidor;
    }
}

