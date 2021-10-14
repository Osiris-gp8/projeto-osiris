package br.com.bandtec.osirisapi.utils.hashing;

import java.util.Objects;

public class HashTable {

    private ListaLigada[] tab;

    public HashTable(Integer numEntradas) {
        this.tab = new ListaLigada[numEntradas];
        iniciarListaLigada();
    }

    // Função Hash devolve o número da entrada de um elemento x
    public Integer funcaoHash(Integer num){

        return num % tab.length;
    }

    public void insere(Integer num){

        Integer indice = funcaoHash(num);
        tab[indice].insereNode(num);
    }

    private void iniciarListaLigada(){
        for (int i = 0; i < tab.length; i++){
            tab[i] = new ListaLigada();
        }
    }

    public boolean buscar(Integer num){

        Integer indice = funcaoHash(num);

        Node node = tab[indice].buscaNode(num);

        if (Objects.isNull(node)){
            return false;
        }

        return true;
    }

    public boolean remove(Integer num){

        if (!buscar(num)){
            return false;
        }

        tab[funcaoHash(num)].removeNode(num);
        return true;
    }
}

