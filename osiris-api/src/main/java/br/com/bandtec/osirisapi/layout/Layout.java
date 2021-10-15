package br.com.bandtec.osirisapi.layout;

import java.io.BufferedReader;

public interface Layout {
    String toCSV();
    String toTXT();
    void fromTXT(String conteudo);
}
