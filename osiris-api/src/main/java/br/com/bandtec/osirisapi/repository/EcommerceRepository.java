package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EcommerceRepository extends JpaRepository<Ecommerce, Integer> {
}
