package br.com.bandtec.osirisapi.layout;

import lombok.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LayoutEvento implements Layout {
    private final DateTimeFormatter FORMATO_DATA_HORA = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private final String TIPO_REGISTRO_CORPO = "02";

    private Integer IdCompra;
    private Integer IdConsumidor;
    private String nomeProduto;
    private Double precoProduto;
    private String categoriaProduto;
    private LocalDateTime dataCompra;
    private Integer idEcommerce;
    private String nomeEcommerce;
    private Integer idCupom;
    private String nomeCupom;
    private Double valorCupom;
    private Integer idDominioStatus;
    private String statusNome;


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
                ", status='" + statusNome + '\'' +
                '}';
    }

    @Override
    public String toCSV() {

        return String.format("%d;%d;%s;%.2f;%s;%s;%s;%s;%.2f;%s%n",
                this.getIdCompra(), this.getIdConsumidor(), this.getNomeProduto(),
                this.getPrecoProduto(), this.getCategoriaProduto(), this.getDataCompra().format(FORMATO_DATA_HORA),
                this.getNomeEcommerce(), this.getNomeCupom(), this.getValorCupom(),
                this.getStatusNome());
    }

    @Override
    public String toTXT() {
        String corpo = "";

        corpo = TIPO_REGISTRO_CORPO;
        corpo += String.format("%05d", this.getIdCompra());
        corpo += String.format("%05d", this.getIdConsumidor());
        corpo += String.format("%-45s", this.getNomeProduto());
        corpo += String.format("%9.2f", this.getPrecoProduto());
        corpo += String.format("%-45s", this.getCategoriaProduto());
        corpo += String.format("%-19s", this.getDataCompra().format(FORMATO_DATA_HORA));
        corpo += String.format("%05d", this.getIdEcommerce());
        corpo += String.format("%-45s", this.getNomeEcommerce());
        corpo += String.format("%05d", this.getIdCupom());
        corpo += String.format("%-45s", this.getNomeCupom());
        corpo += String.format("%7.2f", this.getValorCupom());
        corpo += String.format("%05d", this.getIdDominioStatus());
        corpo += String.format("%-45s%n", this.getStatusNome());

        return corpo;
    }

    @Override
    public void fromTXT(String conteudo) {

        Integer indice = 0;
        String[] linhas = conteudo.split(System.lineSeparator());
        importarLinhas( linhas, indice );

    }

    private void importarLinhas(String[] linhas, Integer indice){
        String linha = linhas[indice];

        String tipoLinha = linha.substring(0, 2);
        if (tipoLinha.equals(TIPO_REGISTRO_CORPO)){
            //TODO verificar se o ecommerce, cupom e status lido existe
            importarDadosDaLinha(linha);
        }

        if (indice < linhas.length-1){
            importarLinhas(linhas, ++indice);
        }
    }

    private void importarDadosDaLinha(String linha){
        IdCompra = Integer.valueOf(linha.substring(2,7).trim());
        IdConsumidor = Integer.valueOf(linha.substring(7,12).trim());
        nomeProduto = linha.substring(12, 56).trim();
        precoProduto = Double.valueOf(linha.substring(57, 66).replace(",", ".").trim());
        categoriaProduto = linha.substring(66, 111).trim();
        dataCompra = LocalDateTime.parse(linha.substring(111, 130), FORMATO_DATA_HORA);
        idEcommerce = Integer.valueOf(linha.substring(130, 135));
        nomeEcommerce = linha.substring(135, 180).trim();
        idCupom = Integer.valueOf(linha.substring(180, 185).trim());
        nomeCupom = linha.substring(185, 230).trim();
        valorCupom = Double.valueOf(linha.substring(230, 237).replace(",", ".").trim());
        idDominioStatus = Integer.valueOf(linha.substring(237, 242));
        statusNome = linha.substring(242, 287).trim();
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
