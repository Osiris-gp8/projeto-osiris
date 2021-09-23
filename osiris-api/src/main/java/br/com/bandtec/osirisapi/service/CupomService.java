package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.repository.CupomRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CupomService {

    private final CupomRepository cupomRepository;

    public void deleteCupom(int idCupom) {

        Optional<Cupom> cupomOptional = cupomRepository.findById(idCupom);
        if (!cupomOptional.isPresent()){
            throw new ApiRequestException("Cupom não existe", HttpStatus.NOT_FOUND);
        }

        cupomRepository.deleteById(idCupom);
    }

    public List<Cupom> buscarCupons() {

        if (cupomRepository.findAll().isEmpty()){
            throw new ApiRequestException("Não existem cupons", HttpStatus.NO_CONTENT);
        }

        return cupomRepository.findAll();
    }

    public Cupom inserirCupom(Cupom cupom){
        return cupomRepository.save(cupom);
    }

    public Integer quantCupom(){return cupomRepository.countAllByCupomQuantidadeId();}

    public Cupom atualizarCupom(Integer idCupom, Cupom cupomAtualizar) {

        Optional<Cupom> cupomOptional = cupomRepository.findById(idCupom);

        if (!cupomOptional.isPresent()) {
            throw new ApiRequestException("Cupom não existe", HttpStatus.NOT_FOUND);
        }

        Cupom cupom = cupomOptional.get();
        cupom.setNomeCupom(cupomAtualizar.getNomeCupom());
        cupom.setDataEmitido(cupomAtualizar.getDataEmitido());
        cupom.setDataValidado(cupomAtualizar.getDataValidado());
        cupom.setUsado(cupomAtualizar.getUsado());
        cupom.setValor(cupomAtualizar.getValor());

        cupomRepository.save(cupom);

        return cupom;
    }

    public Cupom buscarCupom(Integer idCupom) {

        Optional<Cupom> cupomOptional = cupomRepository.findById(idCupom);
        if (!cupomOptional.isPresent()){
            throw new ApiRequestException("Cupom não existe", HttpStatus.NOT_FOUND);
        }

        return cupomOptional.get();
    }

    public List<Cupom> adicionarCupons(List<Cupom> cupons) {

        return cupons
                .stream()
                .map(cupom -> cupomRepository.save(cupom))
                .collect(Collectors.toList());
    }
}
