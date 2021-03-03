package br.com.grupo8.api.models;

public class Consumidor {
    private Integer idConsumidor;
    private String cpf;
    private String nome;
    private Short sexo;
    private Ecommerce ecommerce;

    public Consumidor(Integer idConsumidor, String cpf, String nome, Short sexo, Ecommerce ecommerce) {
        this.idConsumidor = idConsumidor;
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.ecommerce = ecommerce;
    }

    public Integer getIdConsumidor() {
        return idConsumidor;
    }

    public void setIdConsumidor(Integer idConsumidor) {
        this.idConsumidor = idConsumidor;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Short getSexo() {
        return sexo;
    }

    public void setSexo(Short sexo) {
        this.sexo = sexo;
    }

    public Ecommerce getEcommerce() {
        return ecommerce;
    }

    public void setEcommerce(Ecommerce ecommerce) {
        this.ecommerce = ecommerce;
    }
}
