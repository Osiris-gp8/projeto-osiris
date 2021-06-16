package br.com.bandtec.osirisapi.layout;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LayoutAcessoEvento {
    private String diaDaSemana;
    private Integer acessos;
    private Integer eventos;
}
