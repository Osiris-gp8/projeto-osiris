package br.com.bandtec.calculometricas.repository;

import br.com.bandtec.calculometricas.model.dao.DominioStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DominioStatusRepository extends JpaRepository<DominioStatus, Integer> {
}
