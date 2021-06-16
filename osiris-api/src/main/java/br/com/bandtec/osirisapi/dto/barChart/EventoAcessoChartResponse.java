package br.com.bandtec.osirisapi.dto.barChart;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventoAcessoChartResponse {
    private EventoDto eventoDto;
    private AcessoDto acessoDto;
}
