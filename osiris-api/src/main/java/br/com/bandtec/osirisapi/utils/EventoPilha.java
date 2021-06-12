package br.com.bandtec.osirisapi.utils;

import br.com.bandtec.osirisapi.domain.Evento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class EventoPilha {

    private Integer idEvento;
    private Evento eventoAntes;
    private String acao;
}
