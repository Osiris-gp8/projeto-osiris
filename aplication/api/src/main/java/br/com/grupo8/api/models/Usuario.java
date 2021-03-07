package br.com.grupo8.api.models;

public class Usuario {
    private Integer idUsuario;
    private String login;
    private String senha;
    private Integer ecommerce;

    public Usuario(Integer idUsuario, String login, String senha, Integer ecommerce) {
        this.idUsuario = idUsuario;
        this.login = login;
        this.senha = senha;
        this.ecommerce = ecommerce;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getEcommerce() {
        return ecommerce;
    }

    public void setEcommerce(Integer ecommerce) {
        this.ecommerce = ecommerce;
    }
}
