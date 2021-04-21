package br.com.bandtec.calculometricas.layout;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

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
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return String.format("%d;%d;%s;%.2f;%s;%s;%s;s;%.2f;%s%n",
                this.getIdCompra(), this.getIdConsumidor(), this.getNomeProduto(),
                this.getPrecoProduto(), this.getCategoriaProduto(), formatter.format(this.getDataCompra()),
                this.getNomeEcommerce(), this.getNomeCupom(), this.getValorCupom(),
                this.getStatus());
    }

    @Override
    public String toTXT() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String corpo = "";

        corpo = "02";
        corpo += String.format("%05d", this.getIdCompra());
        corpo += String.format("%05d", this.getIdConsumidor());
        corpo += String.format("%-45s", this.getNomeProduto());
        corpo += String.format("%.2f", this.getPrecoProduto());
        corpo += String.format("%-45s", this.getCategoriaProduto());
        corpo += String.format("%-19s", formatter.format(this.getDataCompra()));
        corpo += String.format("%-45s", this.getNomeEcommerce());
        corpo += String.format("%-45s", this.getNomeCupom());
        corpo += String.format("%.2f", this.getValorCupom());
        corpo += String.format("%-45s%n", this.getStatus());

        return corpo;
    }

    public static String header() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date dataHoje = new Date();

        String header = "";

        header += "00RELATORIO";
        header += formatter.format(dataHoje);
        header += "00\n";

        return header;
    }

    public static String trailer(int contRegistro) {
        String trailer = "";

        trailer += "01";
        trailer += String.format("%010d%n", contRegistro);

        return trailer;
    }
}
