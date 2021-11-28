package br.com.bandtec.osirisapi.layout;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class LayoutGenerico{

    public static final String TIPO_REGISTRO_HEADER = "01";
    public static final String TIPO_REGISTRO_CORPO_EVENTO = "02";
    public static final String TIPO_REGISTRO_CORPO_CUPOM = "03";
    public static final String TIPO_REGISTRO_TRAILER = "04";


    private List<LayoutEvento> layoutEventoList;
    private List<LayoutCupom> layoutCupomList;

    public LayoutGenerico() {
        this.layoutEventoList = new ArrayList<>();
        this.layoutCupomList = new ArrayList<>();
    }




    public void importarLinhas(BufferedReader reader) throws IOException {
        String linha = reader.readLine();
        if(linha == null || linha.isEmpty()){
            return;
        }


        String tipoLinha = linha.substring(0, 2);
        if (tipoLinha.equals(TIPO_REGISTRO_CORPO_EVENTO)){
            //TODO verificar se o ecommerce, cupom e status lido existe
            importarEvento(linha);
        }else if (tipoLinha.equals(TIPO_REGISTRO_CORPO_CUPOM)){
            importarCupom(linha);
        }

        importarLinhas(reader);
    }

    public static String header() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date dataHoje = new Date();

        String header = "";

        header += TIPO_REGISTRO_HEADER;
        header += "RELATORIO";
        header += formatter.format(dataHoje);
        header += "00\n";

        return header;
    }

    public static String trailer(int contRegistro) {
        String trailer = "";

        trailer += TIPO_REGISTRO_TRAILER;
        trailer += String.format("%010d%n", contRegistro);

        return trailer;
    }

    private void importarEvento(String linha){
        LayoutEvento layoutEvento = new LayoutEvento();
        layoutEvento.fromTXT(linha);
        this.layoutEventoList.add( layoutEvento );
    }

    private void importarCupom(String linha){
        LayoutCupom layoutCupom = new LayoutCupom();
        layoutCupom.fromTXT(linha);
        this.layoutCupomList.add( layoutCupom );
    }

    public Boolean hasEventoLayout(){
        return !this.layoutEventoList.isEmpty();
    }

    public Boolean hasCupomLayout(){
        return !this.layoutCupomList.isEmpty();
    }
}
