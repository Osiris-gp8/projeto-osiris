package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.dto.barChart.EventoDto;
import br.com.bandtec.osirisapi.views.CupomMaisUsadoView;
import br.com.bandtec.osirisapi.views.RanqueCategoriaView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    @Query(value = "select nome_categoria as categoria, count(*) as quantidade from evento " +
            "group by nome_categoria order by quantidade desc", nativeQuery = true)
    List<RanqueCategoriaView> ranque();

    @Query(value = "select * from evento where id_consumidor_ecommerce = ?", nativeQuery = true)
    List<Evento> findAllByIdConsumidorEcommerce(Integer consumidor);

    @Query(value = "select * from evento where idConsumidorEcommerce = ? and fkCupom is null", nativeQuery = true)
    List<Evento> findByConsumidorEcommerceWithoutCupom(Integer consumidor);

    @Query(value = "select nome_cupom as nome, fk_cupom as cupom,count(*) as quantidades FROM evento " +
            "GROUP BY fk_cupom order by quantidades desc", nativeQuery = true)
    List<CupomMaisUsadoView> cupomMaisUsado();

    @Query(value = "select e.* from evento e, cupom c where e.fk_cupom = c.id_cupom " +
            "and c.usado=false and e.fk_status = 2", nativeQuery = true)
    List<Evento> findAllByCupomAndEventoAndUsadoIsFalseAndFkStatus();

    @Query(value = "select count(*) from evento e, cupom c where e.fk_cupom = c.id_cupom " +
            "and c.usado=false and e.fk_status = 2", nativeQuery = true)
    Integer countAllByCupomAndEventoAndUsadoIsFalseAndFkStatus();

    @Query(value = "select e.* from evento e, ecommerce ec where e.ecommerce_id_ecommerce = ?1 and e.ecommerce_id_ecommerce = ec.id_ecommerce",
            nativeQuery = true)
    List<Evento> findAllByIdEcommerce(Integer idEcommerce);

    List<Evento> findByDataInclusaoBetweenAndEcommerceEquals(LocalDateTime dataInicial, LocalDateTime dataFinal, Ecommerce ecommerce);

    @Query(value = "select count(id_evento) as evento from evento where data_compra between " +
            "getdate()-6 and getdate() group by day(data_compra)", nativeQuery = true)
    List<EventoDto> countEventosByLastWeek();
    List<Evento> findByDataInclusaoBetweenAndEcommerceEquals(LocalDate dataInicial, LocalDate dataFinal, Ecommerce ecommerce);

    @Query(value = "select count(*) from evento e, ecommerce ec where e.ecommerce_id_ecommerce = ?1" +
            " and e.ecommerce_id_ecommerce = ec.id_ecommerce" +
            " and e.cupom_id_cupom is null" +
            " and e.data_compra between ?2 and ?3",
            nativeQuery = true)
    Integer findByEventoSemCupomAndDataCompraBetween(Integer idEcommerce, LocalDate dataInicial, LocalDate dataFinal);

    @Query(value = "select count(*) from evento e, ecommerce ec where e.ecommerce_id_ecommerce = ?1" +
            " and e.ecommerce_id_ecommerce = ec.id_ecommerce" +
            " and e.cupom_id_cupom is not null" +
            " and e.data_compra between ?2 and ?3",
            nativeQuery = true)
    Integer findByEventoComCupomAndDataCompraBetween(Integer idEcommerce, LocalDate dataInicial, LocalDate dataFinal);
}
