package br.com.bandtec.osirisapi.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventoProtocolo {

    @Id
    private String idEventoProtocolo;

    @OneToOne
    private Evento evento;

    private String status;

    private LocalDateTime dataHoraConclusao;
}
