package br.com.bandtec.osirisapi.views;

import org.springframework.beans.factory.annotation.Value;

public interface RanqueCategoria {

    @Value("#{target.categoria}")
    String getNome();

    @Value("#{target.quantidade}")
    Integer getQuantidades();
}
