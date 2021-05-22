package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.DominioStatus;
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

    public List<DominioStatus> getAllDominios() throws NotFoundException {
        List<DominioStatus> dominios = dominioStatusRepository.findAll();
        if (dominios.isEmpty()) {
            throw new NotFoundException("Não existem dominios");
        } else {
            return dominios;
        }
    }

    public void saveDominio(DominioStatus dominioStatus) throws HttpClientErrorException {
        Optional<DominioStatus> dominioStatusOptional = dominioStatusRepository.findOneByNome(dominioStatus.getNome());
        if (dominioStatusOptional.isPresent()){

            if (dominioStatusOptional.get().getNome().equals(dominioStatus.getNome())){

                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Dominio já existente");
            }
        }

        dominioStatusRepository.save(dominioStatus);
    }

    public void deleteDomino(int idDominioStatus) throws NotFoundException {
        if (!dominioStatusRepository.existsById(idDominioStatus)) {
            throw new NotFoundException("Dominio não existente");
        }
        dominioStatusRepository.deleteById(idDominioStatus);
    }

    public void atualizarDominioPeloId(Integer idDominioStatus, DominioStatus dominioStatus) throws NotFoundException {

        Optional<DominioStatus> dominioStatusOptional = dominioStatusRepository.findById(idDominioStatus);

        if (!dominioStatusOptional.isPresent()){
            throw new NotFoundException("Dominio não existente");
        }
        DominioStatus dominioStatusParaAtualizar = dominioStatusOptional.get();

        dominioStatusParaAtualizar.setNome(dominioStatus.getNome());

        dominioStatusRepository.save(dominioStatusParaAtualizar);
    }
}
