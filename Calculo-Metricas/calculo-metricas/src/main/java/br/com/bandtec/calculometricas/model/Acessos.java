package br.com.bandtec.calculometricas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Acessos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAcessos;

    private Integer idConsumidorEcommerce;

    private LocalDateTime inicioAcesso;

    private LocalDateTime fimAcesso;

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

    public LocalDateTime getInicioAcesso() {
        return inicioAcesso;
    }

    public void setInicioAcesso(LocalDateTime inicioAcesso) {
        this.inicioAcesso = inicioAcesso;
    }

    public LocalDateTime getFimAcesso() {
        return fimAcesso;
    }

    public void setFimAcesso(LocalDateTime fimAcesso) {
        this.fimAcesso = fimAcesso;
    }
}

