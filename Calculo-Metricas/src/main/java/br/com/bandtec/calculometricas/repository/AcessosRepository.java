package br.com.bandtec.calculometricas.repository;

import br.com.bandtec.calculometricas.model.dao.Acessos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AcessosRepository extends JpaRepository<Acessos, Integer> {
//    @Query(value = (String) "select count(id_acessos) from acessos where inicio_acesso between current_date()-7 and current_date()")
//    List<String> countAcessosSemana();
//
//    @Query(value = (String) "select count(id_acessos) as quantidade from acessos")
//    List<String> countAcessos();
}
