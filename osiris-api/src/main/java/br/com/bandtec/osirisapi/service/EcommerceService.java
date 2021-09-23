package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.repository.EcommerceRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EcommerceService {

    private final EcommerceRepository ecommerceRepository;

    public List<Ecommerce> getEcommerces() {
        List<Ecommerce> ecommerces = ecommerceRepository.findAll();
        if (ecommerces.isEmpty()) {
            throw new ApiRequestException("Não existem ecommerces", HttpStatus.NO_CONTENT);
        }

        return ecommerces;
    }

    public Ecommerce inserirEcommerce(Ecommerce ecommerce){
        return ecommerceRepository.save(ecommerce);
    }

    public void deletarEcommerce(int idEcommerce) {
        if (!ecommerceRepository.existsById(idEcommerce)) {
            throw new ApiRequestException("Esse ecommerce não existe", HttpStatus.NOT_FOUND);
        }
        ecommerceRepository.deleteById(idEcommerce);
    }

    public Ecommerce atualizarEcommerce(Integer idEcommerce, Ecommerce ecommerce) {

        Optional<Ecommerce> ecommerceParaAtualizarOptional = ecommerceRepository.findById(idEcommerce);

        if (!ecommerceParaAtualizarOptional.isPresent()){
            throw new ApiRequestException("Esse ecommerce não existe", HttpStatus.NOT_FOUND);
        }

        Ecommerce ecommerceParaAtualizar = ecommerceParaAtualizarOptional.get();

        ecommerceParaAtualizar.setCnpj(ecommerce.getCnpj());
        ecommerceParaAtualizar.setNome(ecommerce.getNome());

        return ecommerceRepository.save(ecommerceParaAtualizar);
    }

    public Integer getIdEcommercePeloCnpjENome(String cnpj, String nome) {
        Optional<Ecommerce> ecommerceOptional = ecommerceRepository.findEcommerceByCnpjAndNome(cnpj, nome);

        if (!ecommerceOptional.isPresent()) {
            throw new ApiRequestException("Esse ecommerce não existe", HttpStatus.NOT_FOUND);
        }
        return ecommerceOptional.get().getIdEcommerce();
    }
}
