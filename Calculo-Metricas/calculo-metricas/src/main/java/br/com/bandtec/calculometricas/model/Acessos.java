package br.com.bandtec.calculometricas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Acessos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAcessos;

    private Integer idConsumidorEcommerce;

    private LocalDate inicioAcesso;

    private LocalDate fimAcesso;

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

    public LocalDate getInicioAcesso() {
        return inicioAcesso;
    }

    public void setInicioAcesso(LocalDate inicioAcesso) {
        this.inicioAcesso = inicioAcesso;
    }

    public LocalDate getFimAcesso() {
        return fimAcesso;
    }

    public void setFimAcesso(LocalDate fimAcesso) {
        this.fimAcesso = fimAcesso;
    }
}

