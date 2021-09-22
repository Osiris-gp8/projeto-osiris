package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.domain.Ecommerce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CupomRepository extends JpaRepository<Cupom, Integer> {
    @Query(value = "select * from cupom where usado = false " +
            "and data_validado < current_timestamp()", nativeQuery = true)
    List<Cupom> findAllByUsadoIsFalseAndDataValidadoLessThanTodayNow();

    @Query(value = "select count(*) from cupom where usado = false " +
            "and data_validado < current_timestamp()", nativeQuery = true)
    Integer countAllByUsadoIsFalseAndDataValidadoLessThanTodayNow();

    @Query(value = "select count(id_Cupom) from cupom ", nativeQuery = true)
    Integer countAllByCupomQuantidadeId();

    List<Cupom> findByDataEmitidoBetweenAndEcommerceEquals(LocalDateTime dataInicial, LocalDateTime dataFinal, Ecommerce ecommerce);
}
