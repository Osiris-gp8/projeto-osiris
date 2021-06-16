package br.com.bandtec.osirisapi.dto.barChart;

import org.springframework.beans.factory.annotation.Value;

public interface EventoDto {

    @Value("#{target.evento}")
    Integer getEvento();
}
