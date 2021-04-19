package br.com.bandtec.calculometricas.repository;

import br.com.bandtec.calculometricas.domain.Acessos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AcessosRepository extends JpaRepository<Acessos, Integer> {
    @Query(value = "select count(id_acessos) as quantidade from acessos where inicio_acesso " +
            "between current_date()-7 and current_date()", nativeQuery = true)
    Integer countAcessosSemana();

}
