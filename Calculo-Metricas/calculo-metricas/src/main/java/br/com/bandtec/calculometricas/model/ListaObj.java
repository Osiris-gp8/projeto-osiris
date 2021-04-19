package br.com.bandtec.calculometricas.model;

public class ListaObj <T>{
    private T[] vetor;
    private int nroElem;

    public ListaObj(int tam) {
        this.vetor = (T[])new Object[tam];
        nroElem = 0;
    }

    public boolean adicionar(T elem){
        if(nroElem < this.vetor.length){
            this.vetor[nroElem++] = elem;
            return true;
        }else{
            return false;
        }
    }

    public void exibir(){
        for (int i = 0; i < this.nroElem; i++) {
            System.out.println(this.vetor[i]);
        }
    }

    public int buscar(T elem){
        boolean achou = false;
        int indice = -1;
        for (int i = 0; i <= nroElem; i++) {
            if(vetor[i].equals(elem)){
                indice = i;
                break;
            }
        }

        return indice;
    }

    public String buscarPorIndice(int indice){
        if(indice < nroElem && indice > -1){
            return vetor[indice].toString();
        }else{
            return "Indíce inválido";
        }
    }

    public boolean removerPeloIndice(int indice){
        if(indice < nroElem && indice > -1){
            for (int i = indice; i < nroElem-1; i++) {
                vetor[i] = vetor[i+1];
            }
            nroElem--;
            return true;
        }else{
            return false;
        }
    }

    public boolean removerPeloElemento(T elem){
        return removerPeloIndice(buscar(elem));
    }

    public int getTamanho(){
        return this.nroElem;
    }

    public T getElemento(int indice){
        if(indice < 0 || indice >= nroElem){
            return null;
        }else{
            return vetor[indice];
        }
    }

    public void limpar(){
        for (int i = 0; i < vetor.length; i++) {
            removerPeloIndice(i);
        }
        nroElem = 0;
    }

//    public boolean substituir(int elemAntigo, int elemNovo){
//        int indice = buscar(elemAntigo);
//        if(indice > -1){
//            vetor[indice] = elemNovo;
//            return true;
//        }else{
//            System.out.println("Valor não encontrado");
//            return false;
//        }
//    }
//
//    public int contaOcorrencias(int valor){
//        int ocorrencias = 0;
//        for (int i = 0; i < vetor.length; i++) {
//            if(vetor[i] == valor){
//                ocorrencias++;
//            }
//        }
//
//        return ocorrencias;
//    }
//
//    public boolean addNoInicio(int valor){
//        int aux;
//        if(nroElem <= vetor.length){
//            for (int i = vetor.length-1; i > 0; i--) {
//                vetor[i] = vetor[i-1];
//            }
//            vetor[0] = valor;
//            nroElem++;
//            return true;
//        }else{
//            System.out.println("Lista cheia");
//            return false;
//        }
//    }
}


