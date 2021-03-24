package br.com.grupo8.api.models;

public class Consumidor {
    private Integer idConsumidor;
    private String cpf;
    private String nome;
    private Short sexo;
    private Integer ecommerce;

    public Consumidor(Integer idConsumidor, String cpf, String nome, Short sexo, Integer ecommerce) {
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

    public Integer getEcommerce() {
        return ecommerce;
    }

    public void setEcommerce(Integer ecommerce) {
        this.ecommerce = ecommerce;
    }
}
