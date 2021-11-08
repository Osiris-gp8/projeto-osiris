package br.com.bandtec.osirisapi.views;

import org.springframework.beans.factory.annotation.Value;

public interface RanqueProdutoView {

    @Value("#{target.produto}")
    String getNome();

    @Value("#{target.quantidade}")
    Integer getQuantidades();

}
