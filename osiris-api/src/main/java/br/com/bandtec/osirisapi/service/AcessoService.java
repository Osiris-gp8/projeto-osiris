package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.Acesso;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.repository.AcessoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AcessoService {

    private final AcessoRepository acessoRepository;

    public List<Acesso> getAcessos() {
        List<Acesso> acessos = acessoRepository.findAll();
        if (acessos.isEmpty()) {
            throw new ApiRequestException("Não existem acessos", HttpStatus.NO_CONTENT);
        }

        return acessos;
    }

    public void inserirAcesso(Acesso novoAcesso) {
        acessoRepository.save(novoAcesso);
    }
}
