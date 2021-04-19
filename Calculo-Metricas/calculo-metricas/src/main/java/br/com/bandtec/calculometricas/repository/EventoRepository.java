package br.com.bandtec.calculometricas.repository;

import br.com.bandtec.calculometricas.model.Evento;
import br.com.bandtec.calculometricas.views.CupomMaisUsado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.lang.Integer;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    @Query(value = "select nome_categoria, count(id_compra) as quantidade from evento " +
            "group by nome_categoria order by quantidade", nativeQuery = true)
    List<String> ranque();

    @Query(value = "select * from evento where id_consumidor_ecommerce = ?",nativeQuery = true)
    List<Evento> findAllByIdConsumidorEcommerce(Integer consumidor);

    @Query(value = "select * from evento where idConsumidorEcommerce = ? and fkCupom is null", nativeQuery = true)
    List<Evento> findByConsumidorEcommerWithoutCupom(Integer consumidor);

    @Query(value = "select fk_cupom as cupom, count(*) as quantidades FROM evento GROUP BY fk_cupom HAVING quantidades > 0 order by quantidades" +
            " desc", nativeQuery = true)
    List<CupomMaisUsado> cupomMaisUsado();


}
