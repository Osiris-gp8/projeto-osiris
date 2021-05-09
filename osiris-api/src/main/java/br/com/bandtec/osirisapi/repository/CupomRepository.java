package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CupomRepository extends JpaRepository<Cupom, Integer> {
    @Query(value = "select * from cupom where usado = false " +
            "and data_validado < current_timestamp()", nativeQuery = true)
    List<Cupom> findAllByUsadoIsFalseAndDataValidadoLessThanTodayNow();

    @Query(value = "select count(*) from cupom where usado = false " +
            "and data_validado < current_timestamp()", nativeQuery = true)
    Integer countAllByUsadoIsFalseAndDataValidadoLessThanTodayNow();
}
