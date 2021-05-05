package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.repository.EcommerceRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EcommerceService {

    private final EcommerceRepository ecommerceRepository;

    public List<Ecommerce> getEcommerces() throws NotFoundException {
        List<Ecommerce> ecommerces = ecommerceRepository.findAll();
        if (ecommerces.isEmpty()) {
            throw new NotFoundException("Não existem ecommerces");
        }

        return ecommerces;
    }

    public Ecommerce inserirEcommerce(Ecommerce ecommerce){
        return ecommerceRepository.save(ecommerce);
    }

    public void deletarEcommerce(int idEcommerce) throws NotFoundException {
        if (!ecommerceRepository.existsById(idEcommerce)) {
            throw new NotFoundException("Ecommerce não existe");
        }
        ecommerceRepository.deleteById(idEcommerce);
    }

    public Ecommerce atualizarEcommerce(Integer idEcommerce, Ecommerce ecommerce) throws NotFoundException {

        Optional<Ecommerce> ecommerceParaAtualizarOptional = ecommerceRepository.findById(idEcommerce);

        if (!ecommerceParaAtualizarOptional.isPresent()){
            throw new NotFoundException("Ecommerce não existe");
        }

        Ecommerce ecommerceParaAtualizar = ecommerceParaAtualizarOptional.get();

        ecommerceParaAtualizar.setCnpj(ecommerce.getCnpj());
        ecommerceParaAtualizar.setNome(ecommerce.getNome());

        return ecommerceRepository.save(ecommerceParaAtualizar);
    }
}
