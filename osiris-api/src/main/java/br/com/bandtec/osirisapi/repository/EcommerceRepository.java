package br.com.bandtec.osirisapi.repository;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EcommerceRepository extends JpaRepository<Ecommerce, Integer> {
//    @Query(value = "select idEcommerce from evento where cnpj = ? and nome = ?", nativeQuery = true)
//    int findEcommerceByCnpjEqualsAndNomeEquals();

    int findEcommerceByCnpjEqualsAndNomeEquals(String cnpj, String nomeEcommerce);
    public Optional<Ecommerce> findByNome(String nome);
}
