package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EcommerceRepository extends JpaRepository<Ecommerce, Integer> {
    public Optional<Ecommerce> findByNome(String nome);
}
