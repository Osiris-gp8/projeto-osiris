package br.com.grupo8.api.models;

import java.util.List;

public class ItemCompra {
    private Integer idIntemCompra;
    private Compra compra;
    private Integer produto;

    public ItemCompra(Integer idIntemCompra, Compra compra, Integer produto) {
        this.idIntemCompra = idIntemCompra;
        this.compra = compra;
        this.produto = produto;
    }

    public Integer getIdIntemCompra() {
        return idIntemCompra;
    }

    public void setIdIntemCompra(Integer idIntemCompra) {
        this.idIntemCompra = idIntemCompra;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Integer getProduto() {
        return produto;
    }

    public void setProduto(Integer produto) {
        this.produto = produto;
    }
}
