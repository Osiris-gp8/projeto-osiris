package br.com.bandtec.calculometricas.layout;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class LayoutEvento implements Layout {
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

    public LayoutEvento(Integer idCompra, Integer idConsumidor, String nomeProduto, Double precoProduto,
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

    @Override
    public String toCSV() {
        return null;
    }

    @Override
    public String toTXT() {
        return null;
    }
}
