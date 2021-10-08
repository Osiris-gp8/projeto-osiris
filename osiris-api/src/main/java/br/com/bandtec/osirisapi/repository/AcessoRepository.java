package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.Acesso;
import br.com.bandtec.osirisapi.dto.barChart.AcessoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
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


    @Query(value = "select count(*) from acesso a, ecommerce ec where a.ecommerce_id_ecommerce = ?3" +
            " and a.ecommerce_id_ecommerce = ec.id_ecommerce" +
            " and a.inicio_acesso between ?1 and ?2",
            nativeQuery = true)
    Integer countAllByInicioAcessoAndIdEcommerce(LocalDateTime inicioDiaDataAcesso, LocalDateTime finalDiaDataAcesso, Integer idEcommerce);

    @Query(value = "select count(id_acessos) as quantidade from acesso where inicio_acesso " +
            "between ?1 and ?2", nativeQuery = true)
    Integer countAcessosDeterminadoDia(LocalDateTime inicioDia, LocalDateTime fimDia);
}
