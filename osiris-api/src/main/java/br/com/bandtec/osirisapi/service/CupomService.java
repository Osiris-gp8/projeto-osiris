package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.repository.CupomRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CupomService {

    private final CupomRepository cupomRepository;

    public void deleteCupom(int idCupom) throws NotFoundException{

        Optional<Cupom> cupomOptional = cupomRepository.findById(idCupom);
        if (!cupomOptional.isPresent()){
            throw new NotFoundException("Cupom não existe");
        }

        cupomRepository.deleteById(idCupom);
    }

    public List<Cupom> buscarCupons() throws NotFoundException{

        if (cupomRepository.findAll().isEmpty()){
            throw new NotFoundException("Não existem cupons");
        }

        return cupomRepository.findAll();
    }

    public Cupom inserirCupom(Cupom cupom){
        return cupomRepository.save(cupom);
    }

    public Cupom atualizarCupom(Integer idCupom, Cupom cupomAtualizar) throws NotFoundException {

        Optional<Cupom> cupomOptional = cupomRepository.findById(idCupom);

        if (!cupomOptional.isPresent()) {
            throw new NotFoundException("Cupom não existe");
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

    public Cupom buscarCupom(Integer idCupom) throws NotFoundException {

        Optional<Cupom> cupomOptional = cupomRepository.findById(idCupom);
        if (!cupomOptional.isPresent()){
            throw new NotFoundException("Cupom não existe");
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
