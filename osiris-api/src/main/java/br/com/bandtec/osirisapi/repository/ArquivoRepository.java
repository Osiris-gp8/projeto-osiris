package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArquivoRepository extends JpaRepository<Arquivo, Integer> {

    List<Arquivo> findByStatus(String status);
}
