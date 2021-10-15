package br.com.bandtec.osirisapi.utils.hashing;

public class ListaLigada<T> {

    private Node head;

    public ListaLigada() {
        head = new Node(0);
    }


    public void insereNode(T valor) {
        Node<T> atual = head.getNext();
        Node<T> anterior = head;
        boolean inseriu;
        Node<T> novo = new Node(valor);
        novo.setNext(head.getNext());
        head.setNext(novo);
    }

    public void exibe() {
        Node<T> atual = head.getNext();
        while (atual != null) {
            System.out.println(atual.getInfo());
            atual = atual.getNext();
        }
    }

    public Node buscaNode(T valor) {
        Node<T> atual = head.getNext();
        while (atual != null) {
            if (atual.getInfo() == valor) {
                return atual;
            } else {
                atual = atual.getNext();
            }
        }
        return null;
    }

    public boolean removeNode(T valor) {
        Node<T> ant = head;
        Node<T> atual = head.getNext();
        while (atual != null) {
            if (atual.getInfo() == valor) {
                ant.setNext(atual.getNext());
                return true;
            } else {
                ant = atual;
                atual = atual.getNext();
            }
        }
        return false;
    }

    public int getTamanho() {
        Node<T> atual = head.getNext();
        int tamanho = 0;
        while (atual != null) {
            tamanho = tamanho + 1;
            atual = atual.getNext();
        }
        return tamanho;
    }

    public Node getHead() {
        return head;
    }
}
