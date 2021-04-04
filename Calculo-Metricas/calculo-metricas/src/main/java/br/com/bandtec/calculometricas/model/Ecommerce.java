package br.com.bandtec.calculometricas.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ecommerce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEcommerce;
    private String cnpj;
    private String nome;

    public Integer getIdEcommerce() {
        return idEcommerce;
    }

    public void setIdEcommerce(Integer idEcommerce) {
        this.idEcommerce = idEcommerce;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
