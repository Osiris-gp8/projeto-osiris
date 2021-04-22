package br.com.bandtec.osirisapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PieChartValuesDTO {

    private String label;
    private Double value;
}
