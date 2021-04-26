package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AcessoRepository extends JpaRepository<Acesso, Integer> {
    @Query(value = "select count(id_acessos) as quantidade from acessos where inicio_acesso " +
            "between current_date()-7 and current_date()", nativeQuery = true)
    Integer countAcessosSemana();

}
