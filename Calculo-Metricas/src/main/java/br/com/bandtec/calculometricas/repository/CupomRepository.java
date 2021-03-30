package br.com.bandtec.calculometricas.repository;

import br.com.bandtec.calculometricas.model.dao.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CupomRepository extends JpaRepository<Cupom, Integer> {
}
