package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "select * from usuario where login = ?1 and senha = ?2", nativeQuery = true)
    Optional<Usuario> findByLoginEqualsAndSenhaEquals(String login, String senha);

    Optional<Usuario> findByLogin(String Login);
}
