package br.com.bandtec.calculometricas.model;

import java.time.LocalDateTime;

public class Layout {
    private Integer IdCompra;
    private Integer IdConsumidor;
    private String nomeProduto;
    private Double precoProduto;
    private String categoriaProduto;
    private LocalDateTime dataCompra;
    private String nomeEcommerce;
    private String nomeCupom;
    private Double valorCupom;
    private String status;

    public Layout(Integer idCompra, Integer idConsumidor, String nomeProduto, Double precoProduto,
                  String categoriaProduto, LocalDateTime dataCompra, String nomeEcommerce, String nomeCupom,
                  Double valorCupom, String status) {
        IdCompra = idCompra;
        IdConsumidor = idConsumidor;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.categoriaProduto = categoriaProduto;
        this.dataCompra = dataCompra;
        this.nomeEcommerce = nomeEcommerce;
        this.nomeCupom = nomeCupom;
        this.valorCupom = valorCupom;
        this.status = status;
    }

    public Integer getIdCompra() {
        return IdCompra;
    }

    public void setIdCompra(Integer idCompra) {
        IdCompra = idCompra;
    }

    public Integer getIdConsumidor() {
        return IdConsumidor;
    }

    public void setIdConsumidor(Integer idConsumidor) {
        IdConsumidor = idConsumidor;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public String getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(String categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getNomeEcommerce() {
        return nomeEcommerce;
    }

    public void setNomeEcommerce(String nomeEcommerce) {
        this.nomeEcommerce = nomeEcommerce;
    }

    public String getNomeCupom() {
        return nomeCupom;
    }

    public void setNomeCupom(String nomeCupom) {
        this.nomeCupom = nomeCupom;
    }

    public Double getValorCupom() {
        return valorCupom;
    }

    public void setValorCupom(Double valorCupom) {
        this.valorCupom = valorCupom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Layout{" +
                "IdCompra=" + IdCompra +
                ", IdConsumidor=" + IdConsumidor +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", precoProduto=" + precoProduto +
                ", categoriaProduto='" + categoriaProduto + '\'' +
                ", dataCompra=" + dataCompra +
                ", nomeEcommerce='" + nomeEcommerce + '\'' +
                ", nomeCupom='" + nomeCupom + '\'' +
                ", valorCupom=" + valorCupom +
                ", status='" + status + '\'' +
                '}';
    }
}
