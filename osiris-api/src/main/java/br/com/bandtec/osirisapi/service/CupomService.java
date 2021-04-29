package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.repository.CupomRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CupomService {

    private final CupomRepository cupomRepository;

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

}
