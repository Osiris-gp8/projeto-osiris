package br.com.bandtec.osirisapi.utils;

import org.springframework.stereotype.Component;

@Component
public class FilaObj<T> {

    private Integer tamanho;
    private T[] fila;

    public FilaObj() {
        tamanho = 0;
        fila = (T[]) new Object[100];
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }


    public boolean isFull() {
        return tamanho == fila.length;
    }

    public void insert(T info) {
        if (isFull()) {
            System.out.println("Fila cheia");
            return;
        }

        fila[tamanho++] = info;
    }

    public Integer getTamanho() {
        return this.tamanho;
    }

    public T get(int id){
        return fila[id];
    }

    public T peek() {
        return fila[0];
    }

    public T poll() {
        T primeiro = peek();

        if (!isEmpty()) {

            for (int i = 0; i < tamanho - 1; i++) {
                fila[i] = fila[i + 1];
            }

            fila[tamanho - 1] = null;

            tamanho--;
        }

        return primeiro;
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("Fila vazia");
        } else {
            for (int i = 0; i < tamanho; i++) {
                System.out.println(fila[i]);
            }
        }
    }

}
