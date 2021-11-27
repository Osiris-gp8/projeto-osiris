package br.com.bandtec.osirisapi.views;

import org.springframework.beans.factory.annotation.Value;

public interface RanqueCategoriaView {

    @Value("#{target.ranque}")
    Integer getRanque();

    @Value("#{target.nome}")
    String getNome();

    @Value("#{target.quantidade}")
    Integer getQuantidades();
}
