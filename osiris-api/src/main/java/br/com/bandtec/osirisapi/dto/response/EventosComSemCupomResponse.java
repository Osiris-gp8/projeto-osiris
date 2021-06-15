package br.com.bandtec.osirisapi.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventosComSemCupomResponse {
    private Integer contagemEventosSemCupom;
    private Integer contagemEventosComCupom;
}
