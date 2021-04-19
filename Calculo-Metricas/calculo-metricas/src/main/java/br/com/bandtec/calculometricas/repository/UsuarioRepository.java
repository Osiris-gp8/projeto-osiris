package br.com.bandtec.calculometricas.repository;

import br.com.bandtec.calculometricas.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
