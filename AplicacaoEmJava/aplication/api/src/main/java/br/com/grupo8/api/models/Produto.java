package br.com.grupo8.api.models;

import java.util.List;

public class Produto {
    private Integer idProduto;
    private String nome;
    private Double preco;
    private Ecommerce ecommerce;
    private Integer categorias;

    public Produto(Integer idProduto, String nome, Double preco, Ecommerce ecommerce, Integer categorias) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.preco = preco;
        this.ecommerce = ecommerce;
        this.categorias = categorias;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Ecommerce getEcommerce() {
        return ecommerce;
    }

    public void setEcommerce(Ecommerce ecommerce) {
        this.ecommerce = ecommerce;
    }

    public Integer getCategorias() {
        return categorias;
    }

    public void setCategorias(Integer categorias) {
        this.categorias = categorias;
    }
}
