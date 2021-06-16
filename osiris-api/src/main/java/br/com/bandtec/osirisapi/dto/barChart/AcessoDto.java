package br.com.bandtec.osirisapi.dto.barChart;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AcessoDto {
    private LocalDate diaDaSemana;
    private Integer acessos;
}
