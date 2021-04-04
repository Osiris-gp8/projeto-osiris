package br.com.bandtec.calculometricas.repository;

import br.com.bandtec.calculometricas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
