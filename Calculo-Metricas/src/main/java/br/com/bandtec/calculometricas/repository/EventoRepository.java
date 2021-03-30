package br.com.bandtec.calculometricas.repository;

import br.com.bandtec.calculometricas.model.dao.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
//    @Query(value = (String) "select count(id_compra) as quantidade from evento")
//    List<String> countEvento();
//
//    @Query(value = (String) "select nome_categoria, count(id_compra) as quantidade from evento " +
//            "group by nome_categoria order by quantidade desc limit 5")
//    List<String> ranqueCategoria();

    @Override
    long count();
}
