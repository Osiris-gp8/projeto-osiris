package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.DominioStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DominioStatusRepository extends JpaRepository<DominioStatus, Integer> {

    Optional<DominioStatus> findOneByNome(String nome);
}
