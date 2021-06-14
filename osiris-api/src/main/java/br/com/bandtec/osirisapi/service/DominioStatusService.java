package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.DominioStatus;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.repository.DominioStatusRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DominioStatusService {

    private final DominioStatusRepository dominioStatusRepository;

    public List<DominioStatus> getAllDominios() {
        List<DominioStatus> dominios = dominioStatusRepository.findAll();
        if (dominios.isEmpty()) {
            throw new ApiRequestException("Não existem dominios", HttpStatus.NO_CONTENT);
        } else {
            return dominios;
        }
    }

    public void saveDominio(DominioStatus dominioStatus) {
        Optional<DominioStatus> dominioStatusOptional = dominioStatusRepository.findOneByNome(dominioStatus.getNome());
        if (dominioStatusOptional.isPresent()){

            if (dominioStatusOptional.get().getNome().equals(dominioStatus.getNome())){

                throw new ApiRequestException("Dominio já existente", HttpStatus.BAD_REQUEST);
            }
        }

        dominioStatusRepository.save(dominioStatus);
    }

    public void deleteDomino(int idDominioStatus) {
        if (!dominioStatusRepository.existsById(idDominioStatus)) {
            throw new ApiRequestException("Dominio não existente", HttpStatus.NOT_FOUND);
        }
        dominioStatusRepository.deleteById(idDominioStatus);
    }

    public void atualizarDominioPeloId(Integer idDominioStatus, DominioStatus dominioStatus) {

        Optional<DominioStatus> dominioStatusOptional = dominioStatusRepository.findById(idDominioStatus);

        if (!dominioStatusOptional.isPresent()){
            throw new ApiRequestException("Dominio não existente", HttpStatus.BAD_REQUEST);
        }
        DominioStatus dominioStatusParaAtualizar = dominioStatusOptional.get();

        dominioStatusParaAtualizar.setNome(dominioStatus.getNome());

        dominioStatusRepository.save(dominioStatusParaAtualizar);
    }
}
