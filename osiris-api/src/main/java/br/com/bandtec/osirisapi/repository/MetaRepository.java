package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.domain.Meta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MetaRepository extends JpaRepository<Meta, Integer> {
    List<Meta> findAllByDataInicioBetweenAndEcommerceEquals(LocalDateTime dataInicial, LocalDateTime dataFinal, Ecommerce ecommerce);
}
