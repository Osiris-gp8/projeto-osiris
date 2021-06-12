package br.com.bandtec.osirisapi.dto.response;

import br.com.bandtec.osirisapi.domain.Evento;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EventoProtocoloResponse {

    private String idEventoProtocolo;
    private Evento evento;
    private String status;
    private LocalDateTime dataHoraConclusao;
}
