package br.com.bandtec.osirisapi.dto.request.dash;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AcessosVendasUltimosSeteDias {

    private Integer vendas;
    private Integer acessos;
    private LocalDate data;
    private String diaDaSemana;
}
