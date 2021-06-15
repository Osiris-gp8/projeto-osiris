package br.com.bandtec.osirisapi.converter.implementation;

import br.com.bandtec.osirisapi.converter.EventoConverter;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.domain.EventoProtocolo;
import br.com.bandtec.osirisapi.dto.response.EventoProtocoloResponse;
import br.com.bandtec.osirisapi.dto.response.EventosSemCupomResponse;
import br.com.bandtec.osirisapi.utils.enums.EventoFilaEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventoConverterImplementation implements EventoConverter {

    @Override
    public EventoProtocolo eventoToEventoProtocolo(Evento evento, String protocolo) {
        return EventoProtocolo.builder()
                .idEventoProtocolo(protocolo)
                .evento(evento)
                .status(EventoFilaEnum.NA_FILA)
                .build();
    }

    @Override
    public EventoProtocoloResponse eventoProtocoloToEventoProtocoloResponse(EventoProtocolo eventoProtocolo) {
        return EventoProtocoloResponse.builder()
                .idEventoProtocolo(eventoProtocolo.getIdEventoProtocolo())
                .evento(eventoProtocolo.getEvento())
                .status(eventoProtocolo.getStatus())
                .dataHoraConclusao(eventoProtocolo.getDataHoraConclusao())
                .build();

    }

    @Override
    public List<EventoProtocoloResponse> eventoProtocoloToEventoProtocoloResponse(List<EventoProtocolo> eventoProtocolos) {

        List<EventoProtocoloResponse> eventosProtocoloResponse = new ArrayList<>();

        for (EventoProtocolo eventoProtocolo : eventoProtocolos){
            eventosProtocoloResponse.add(EventoProtocoloResponse.builder()
                    .idEventoProtocolo(eventoProtocolo.getIdEventoProtocolo())
                    .evento(eventoProtocolo.getEvento())
                    .status(eventoProtocolo.getStatus())
                    .dataHoraConclusao(eventoProtocolo.getDataHoraConclusao())
                    .build());
        }

        return eventosProtocoloResponse;
    }

    @Override
    public EventosSemCupomResponse eventoToEventosSemCupomResponse(Integer contagemEventos) {
        return EventosSemCupomResponse.builder()
                .contagemEventosSemCupom(contagemEventos)
                .build();
    }
}
