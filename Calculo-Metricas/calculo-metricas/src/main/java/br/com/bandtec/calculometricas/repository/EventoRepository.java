package br.com.bandtec.calculometricas.repository;

import br.com.bandtec.calculometricas.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.lang.Integer;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    @Query(value = "select nome_categoria, count(id_compra) as quantidade from evento " +
            "group by nome_categoria order by quantidade desc limit 5", nativeQuery = true)
    List<String> ranque();

    @Query(value = "select * from evento where id_consumidor_ecommerce = ?",nativeQuery = true)
    List<Evento> findAllByIdConsumidorEcommerce(Integer consumidor);

}
