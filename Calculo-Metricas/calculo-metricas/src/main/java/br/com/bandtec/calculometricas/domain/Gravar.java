package br.com.bandtec.calculometricas.domain;

import br.com.bandtec.calculometricas.layout.LayoutEvento;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class Gravar {
    public static void gravarCsv(ListaObj<LayoutEvento> lista, String nomeArquivo){
        FileWriter arquivo = null;
        Formatter saida = null;
        boolean erro = false;


        nomeArquivo += ".csv";

        try {
            arquivo = new FileWriter(nomeArquivo, true);
            saida = new Formatter(arquivo);
        } catch (IOException e) {
            System.err.println("Erro ao abrir arquivo: " + e.getMessage());
            System.exit(1);
        }

        try{
            for (int i = 0; i < lista.getTamanho(); i++) {
                LayoutEvento registro = lista.getElemento(i);

                saida.format(registro.toCSV());
            }
        } catch (FormatterClosedException e) {
            System.err.println("Erro ao gravar: " + e.getMessage());
            erro = true;
        } finally {
            saida.close();
            try{
                arquivo.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar arquivo: " + e.getMessage());
                erro = true;
            }

            if(erro){
                System.exit(1);
            }
        }
    }

    public static void gravarTxt(ListaObj<LayoutEvento> lista, String nomeArquivo){
        BufferedWriter saida = null;
        nomeArquivo += ".txt";
        int contRegistro = 0;
        try{
            saida = new BufferedWriter(new FileWriter(nomeArquivo, true));
        } catch (IOException e) {
            System.err.println("Erro ao abrir arquivo: " + e.getMessage());
        }
        try{

            saida.append(LayoutEvento.header());

            for (int i = 0; i < lista.getTamanho(); i++) {
                LayoutEvento registro = lista.getElemento(i);

                saida.append(registro.toTXT());
                contRegistro++;

            }

            saida.append(LayoutEvento.trailer(contRegistro));

            saida.close();

        } catch (IOException e) {
            System.err.println("Erro ao gravar arquivo: " + e.getMessage());
        }
    }
}
