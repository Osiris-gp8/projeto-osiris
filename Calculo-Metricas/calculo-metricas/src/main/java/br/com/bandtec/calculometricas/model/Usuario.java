package br.com.bandtec.calculometricas.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idUsuario;
    private String login;
    private String senha;
    private Integer fkEcommerce;

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

    public Integer getFkEcommerce() {
        return fkEcommerce;
    }

    public void setFkEcommerce(Integer fkEcommerce) {
        this.fkEcommerce = fkEcommerce;
    }
}
