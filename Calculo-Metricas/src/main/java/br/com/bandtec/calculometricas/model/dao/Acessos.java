package br.com.bandtec.calculometricas.model.dao;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Acessos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAcessos;

    private Integer idConsumidorEcommerce;

    @Temporal(value = TemporalType.DATE)
    private Date inicioAcesso;

    @Temporal(value = TemporalType.DATE)
    private Date fimAcesso;

//    @Formula(value = (String) "select count(id_acessos) from acessos where inicio_acesso " +
//            "between current_date()-7 and current_date()")
//    private String media;

    public Integer getIdAcessos() {
        return idAcessos;
    }

    public void setIdAcessos(Integer idAcessos) {
        this.idAcessos = idAcessos;
    }

    public Integer getIdConsumidorEcommerce() {
        return idConsumidorEcommerce;
    }

    public void setIdConsumidorEcommerce(Integer idConsumidorEcommerce) {
        this.idConsumidorEcommerce = idConsumidorEcommerce;
    }

    public Date getInicioAcesso() {
        return inicioAcesso;
    }

    public void setInicioAcesso(Date inicioAcesso) {
        this.inicioAcesso = inicioAcesso;
    }

    public Date getFimAcesso() {
        return fimAcesso;
    }

    public void setFimAcesso(Date fimAcesso) {
        this.fimAcesso = fimAcesso;
    }
}
