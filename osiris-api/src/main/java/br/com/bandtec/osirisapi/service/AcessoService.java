package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.Acesso;
import br.com.bandtec.osirisapi.dto.response.UsuarioResponse;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.repository.AcessoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AcessoService {

    private final AcessoRepository acessoRepository;
    private final UserInfo userInfo;

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

    public Integer getAcessosPorDia(LocalDate data) {

        UsuarioResponse usuario = userInfo.getUsuario();

        LocalDateTime inicioDiaAcesso = calcularInicioDoDia(data);
        LocalDateTime fimDiaAcesso = calcularFinalDoDia(data);

        Integer contagemAcessosPorData = acessoRepository.countAllByInicioAcessoAndIdEcommerce(
                inicioDiaAcesso,
                fimDiaAcesso,
                usuario.getEcommerce().getIdEcommerce());

        return contagemAcessosPorData;
    }

    private LocalDateTime calcularFinalDoDia(LocalDate data){

        LocalTime ltFimDiaCompra = LocalTime.of(23,59,59);

        return LocalDateTime.of(data, ltFimDiaCompra);
    }

    private LocalDateTime calcularInicioDoDia(LocalDate data){
        LocalTime ltInicioDiaCompra = LocalTime.of(0,0,0);

        return LocalDateTime.of(data, ltInicioDiaCompra);
    }

    public Integer countAcessoDeterminadoDia(LocalDateTime inicioDia,LocalDateTime fimDia)
    {
        return acessoRepository.countAcessosDeterminadoDia(inicioDia,fimDia);
    }
}
