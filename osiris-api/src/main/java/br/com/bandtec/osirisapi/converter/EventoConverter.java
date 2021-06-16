package br.com.bandtec.osirisapi.converter;

import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.domain.EventoProtocolo;
import br.com.bandtec.osirisapi.dto.barChart.AcessoDto;
import br.com.bandtec.osirisapi.dto.barChart.EventoAcessoChartResponse;
import br.com.bandtec.osirisapi.dto.barChart.EventoDto;
import br.com.bandtec.osirisapi.dto.response.EventoProtocoloResponse;
import br.com.bandtec.osirisapi.dto.response.EventosComSemCupomResponse;

import java.util.List;

public interface EventoConverter {

    EventoProtocolo eventoToEventoProtocolo(Evento evento, String protocolo);

    EventoProtocoloResponse eventoProtocoloToEventoProtocoloResponse(EventoProtocolo eventoProtocolo);

    List<EventoProtocoloResponse> eventoProtocoloToEventoProtocoloResponse(List<EventoProtocolo> eventoProtocolos);

    List<EventoAcessoChartResponse> eventoDtoAcessoDtoToEventoAcessoChartResponse(List<EventoDto> eventoDtoList,
                                                                                  List<AcessoDto> acessoDtoList);

    EventosComSemCupomResponse eventoToEventosSemCupomResponse(Integer contagemEventos, Integer contagemEventosComCupom);
}
