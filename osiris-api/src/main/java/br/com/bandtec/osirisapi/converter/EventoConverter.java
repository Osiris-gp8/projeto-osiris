package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.domain.EventoProtocolo;
import br.com.bandtec.osirisapi.dto.response.EventoProtocoloResponse;
import br.com.bandtec.osirisapi.dto.response.EventosSemCupomResponse;

import java.util.List;

public interface EventoConverter {

    EventoProtocolo eventoToEventoProtocolo(Evento evento, String protocolo);

    EventoProtocoloResponse eventoProtocoloToEventoProtocoloResponse(EventoProtocolo eventoProtocolo);

    List<EventoProtocoloResponse> eventoProtocoloToEventoProtocoloResponse(List<EventoProtocolo> eventoProtocolos);

    EventosSemCupomResponse eventoToEventosSemCupomResponse(Integer contagemEventos);
}
