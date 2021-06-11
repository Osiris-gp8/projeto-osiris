package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.EventoProtocolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventoProtocoloRepository extends JpaRepository<EventoProtocolo, String> {

    @Query(value = "select ep.* from evento_protocolo ep," +
            " evento e, ecommerce ec" +
            " where e.ecommerce_id_ecommerce = ?1 and e.ecommerce_id_ecommerce = ec.id_ecommerce and" +
            " id_evento_protocolo = ?2",
            nativeQuery = true)
    Optional<EventoProtocolo> findAllByIdEcommerceAndIdProtocolo(Integer idEcommerce, String idProtocolo);

    List<EventoProtocolo> findAllByStatusEquals(String status);

    @Query(value = "select ep.* from evento_protocolo ep," +
            " evento e, ecommerce ec" +
            " where e.ecommerce_id_ecommerce = ?1 and e.ecommerce_id_ecommerce = ec.id_ecommerce",
            nativeQuery = true)
    List<EventoProtocolo> findAllEventoProtocoloByIdEcommerce(Integer idEcommerce);
}
