package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArquivoRepository extends JpaRepository<Arquivo, Integer> {

    List<Arquivo> findByStatus(String status);

    Integer countAllByStatus(String status);

    @Query("SELECT SUM(a.tamanhoBytes) FROM Arquivo a WHERE a.status = 'SUCESSO'")
    Long sumTamanhoEmBytes();
}
