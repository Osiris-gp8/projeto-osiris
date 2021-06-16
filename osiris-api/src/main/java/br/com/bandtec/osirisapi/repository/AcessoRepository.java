package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.Acesso;
import br.com.bandtec.osirisapi.dto.barChart.AcessoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AcessoRepository extends JpaRepository<Acesso, Integer> {
    @Query(value = "select count(id_acessos) as quantidade from acessos where inicio_acesso " +
            "between current_date()-7 and current_date()", nativeQuery = true)
    Integer countAcessosSemana();

    @Query(value =
            "select day(inicio_acesso) as dia_da_semana, count(id_acessos) as acessos from acesso " +
                    "where inicio_acesso between getdate()-6 and getdate() " +
                    "group by day(inicio_acesso)", nativeQuery = true)
    List<AcessoDto> countAcessosByLastWeek();
}
