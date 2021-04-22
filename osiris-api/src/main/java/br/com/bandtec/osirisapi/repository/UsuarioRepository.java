package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
