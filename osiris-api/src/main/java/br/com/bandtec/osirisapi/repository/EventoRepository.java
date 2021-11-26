package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.dto.barChart.EventoDto;
import br.com.bandtec.osirisapi.views.CountAcessoEventos;
import br.com.bandtec.osirisapi.views.CupomMaisUsadoView;
import br.com.bandtec.osirisapi.views.RanqueCategoriaView;
import br.com.bandtec.osirisapi.views.RanqueProdutoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    @Query(value = "with top_products as (\n" +
            "\tselect e.nome_produto as nome, count(*) as quantidade \n" +
            "\tfrom evento e, ecommerce ec \n" +
            "\twhere e.ecommerce_id_ecommerce = ?1 and e.ecommerce_id_ecommerce = ec.id_ecommerce \n" +
            "\tand date(data_compra) between ?2 and ?3\n" +
            "\tgroup by e.nome_produto order by quantidade desc limit 5\n" +
            ")\n" +
            "select DENSE_RANK()\n" +
            "over ( order by quantidade desc ) as ranque,\n" +
            "evento, quantidade \n" +
            "from top_products", nativeQuery = true)
    List<RanqueCategoriaView> ranqueNomeCategoriaView(Integer idEcommerce, LocalDate inicio, LocalDate fim);

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

    @Query(value = "select count(*) from evento e, ecommerce ec where e.ecommerce_id_ecommerce = ?3" +
            " and e.ecommerce_id_ecommerce = ec.id_ecommerce" +
            " and e.data_compra between ?1 and ?2",
            nativeQuery = true)
    Integer countAllByDataCompraAndIdEcommerce(LocalDateTime inicioDiaDataCompra, LocalDateTime finalDiaDataCompra, Integer idEcommerce);

    @Query(value = "select count(1), e.data_compra from evento e, ecommerce ec where e.ecommerce_id_ecommerce = ?3" +
            " and e.ecommerce_id_ecommerce = ec.id_ecommerce" +
            " and e.data_compra between ?1 and ?2" +
            " group by day(e.data_compra)",
            nativeQuery = true)
    Integer findByEventoComCupom(Integer idEcommerce);

    @Query(value = "select count(cupom_id_cupom) from evento ", nativeQuery = true)
    Integer countAllByEventoQuantidadeCuponsUsados();

    @Query(value = "select count(id_consumidor_ecommerce) from evento where id_consumidor_ecommerce = ?3" +
            " and data_compra between ?1 and ?2 ", nativeQuery = true)
    Integer countAcessosDeterminadoDia(LocalDateTime inicioDia, LocalDateTime fimDia,Integer ecommerce);

    @Query(value = "select count(distinct id_consumidor_ecommerce) from evento where ecommerce_id_ecommerce = ?1", nativeQuery = true)
    Integer countClientesDistintos(Integer ecommerce);

    @Query(value = "" +
        "with eventos_days as ( " +
            "SELECT dayname(data_compra) as data_compra , count(1) as vendas from evento " +
            "where date(data_compra) BETWEEN ?1 and ?2 " +
            "and ecommerce_id_ecommerce = ?3 " +
            "group by dayname(data_compra) " +
        ")," +
        "acessos_days as ( " +
            "SELECT dayname(inicio_acesso) as inicio_acesso, count(1) as acessos from acesso " +
            "where date(inicio_acesso) BETWEEN ?1 and ?2 " +
            "and ecommerce_id_ecommerce = ?3 " +
            "group by dayname(inicio_acesso) " +
        ")" +
        "SELECT e.data_compra as diaDaSemana, e.vendas, a.acessos " +
        "from eventos_days e " +
        "inner join acessos_days a " +
        " on e.data_compra = a.inicio_acesso" +
            "", nativeQuery = true)
    List<CountAcessoEventos> countEventosAndAcessosBetween(LocalDate inicio, LocalDate fim, Integer idEcommerce);
}
