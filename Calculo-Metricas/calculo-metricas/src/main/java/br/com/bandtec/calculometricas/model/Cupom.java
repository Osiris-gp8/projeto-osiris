package br.com.bandtec.calculometricas.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Cupom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCupom;
    private String nomeCupom;
    private Double valor;
    private LocalDateTime dataExpirado;
    private LocalDateTime dataValidado;

    public Integer getIdCupom() {
        return idCupom;
    }

    public void setIdCupom(Integer idCupom) {
        this.idCupom = idCupom;
    }

    public String getNomeCupom() {
        return nomeCupom;
    }

    public void setNomeCupom(String nomeCupom) {
        this.nomeCupom = nomeCupom;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataExpirado() {
        return dataExpirado;
    }

    public void setDataExpirado(LocalDateTime dataExpirado) {
        this.dataExpirado = dataExpirado;
    }

    public LocalDateTime getDataValidado() {
        return dataValidado;
    }

    public void setDataValidado(LocalDateTime dataValidado) {
        this.dataValidado = dataValidado;
    }
}
