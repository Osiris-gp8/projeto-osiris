package br.com.bandtec.calculometricas.repository;

import br.com.bandtec.calculometricas.model.Ecommerce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EcommerceRepository extends JpaRepository<Ecommerce, Integer> {
}
