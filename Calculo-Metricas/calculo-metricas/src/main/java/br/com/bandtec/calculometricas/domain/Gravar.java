package br.com.bandtec.calculometricas.domain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class Gravar {
    public static void gravarCsv(ListaObj<Layout> lista, String nomeArquivo){
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
                Layout registro = lista.getElemento(i);

                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                saida.format("%d;%d;%s;%.2f;%s;%s;%s;s;%.2f;%s",
                        registro.getIdCompra(), registro.getIdConsumidor(), registro.getNomeProduto(),
                        registro.getPrecoProduto(), registro.getCategoriaProduto(), formatter.format(registro.getDataCompra()),
                        registro.getNomeEcommerce(), registro.getNomeCupom(), registro.getValorCupom(),
                        registro.getStatus());

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

    public static void gravarTxt(ListaObj<Layout> lista, String nomeArquivo){
        BufferedWriter saida = null;
        nomeArquivo += ".txt";
        try{
            saida = new BufferedWriter(new FileWriter(nomeArquivo, true));
        } catch (IOException e) {
            System.err.println("Erro ao abrir arquivo: " + e.getMessage());
        }
        try{
            String header = "";
            String corpo = "";
            String trailer = "";
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date dataHoje = new Date();

            header += "00RELATORIO";
            header += formatter.format(dataHoje);
            header += "00";

            saida.append(header + "\n");

            for (int i = 0; i < lista.getTamanho(); i++) {
                Layout registro = lista.getElemento(i);
                corpo = "02";
                corpo += String.format("%05d", registro.getIdCompra());
                corpo += String.format("%05d", registro.getIdConsumidor());
                corpo += String.format("%-45s", registro.getNomeProduto());
                corpo += String.format("%.2f", registro.getPrecoProduto());
                corpo += String.format("%-45s", registro.getCategoriaProduto());
                corpo += String.format("%-19s", formatter.format(registro.getDataCompra()));
                corpo += String.format("%-45s", registro.getNomeEcommerce());
                corpo += String.format("%-45s", registro.getNomeCupom());
                corpo += String.format("%.2f", registro.getValorCupom());
                corpo += String.format("%-45s", registro.getStatus());

                saida.append(corpo + "\n");

            }

            trailer += "01";
            trailer += String.format("%010d", lista.getTamanho());
            saida.append(trailer + "\n");

            saida.close();

        } catch (IOException e) {
            System.err.println("Erro ao gravar arquivo: " + e.getMessage());
        }
    }
}
