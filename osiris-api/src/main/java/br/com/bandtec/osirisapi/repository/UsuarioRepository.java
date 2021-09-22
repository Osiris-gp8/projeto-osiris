package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "select * from usuario where login_usuario = ?1 and senha = ?2", nativeQuery = true)
    Optional<Usuario> findByLoginEqualsAndSenhaEquals(String login, String senha);

    Optional<Usuario> findByLoginUsuario(String loginUsuario);

    @Query(value = "select * from usuario u, ecommerce ec where u.ecommerce_id_ecommerce = ?1" +
            " and ec.id_ecommerce = u.ecommerce_id_ecommerce",
        nativeQuery = true)
    List<Usuario> findAllByEcommerceIdEcommerce(Integer idEcommerce);
}
