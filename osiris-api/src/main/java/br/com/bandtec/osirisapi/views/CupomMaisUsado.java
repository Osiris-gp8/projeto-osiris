package br.com.bandtec.osirisapi.views;

import org.springframework.beans.factory.annotation.Value;

public interface CupomMaisUsado {

    @Value("#{target.nome}")
    String getNome();

    @Value("#{target.quantidades}")
    Integer getQuantidades();

    @Value("#{target.cupom}")
    Integer getCupom();

}