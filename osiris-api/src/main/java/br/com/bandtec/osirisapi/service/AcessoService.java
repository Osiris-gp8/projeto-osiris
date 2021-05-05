package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.Acesso;
import br.com.bandtec.osirisapi.repository.AcessoRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AcessoService {

    private final AcessoRepository acessoRepository;

    public List<Acesso> getAcessos() throws NotFoundException {
        List<Acesso> acessos = acessoRepository.findAll();
        if (acessos.isEmpty()) {
            throw new NotFoundException("Não existem acessos");
        }

        return acessos;
    }

    public void inserirAcesso(Acesso novoAcesso) {
        acessoRepository.save(novoAcesso);
    }
}
