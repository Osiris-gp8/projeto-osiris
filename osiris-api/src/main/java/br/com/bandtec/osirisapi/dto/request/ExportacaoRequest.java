package br.com.bandtec.osirisapi.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ExportacaoRequest {

    public static final Integer TIPO_CORPO_AMBOS = 0;
    public static final Integer TIPO_CORPO_EVENTO = 1;
    public static final Integer TIPO_CORPO_CUPOM = 2;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent
    private LocalDate dataInicio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent
    private LocalDate dataFim;

    @PositiveOrZero
    private Integer tipoCorpo;

}
