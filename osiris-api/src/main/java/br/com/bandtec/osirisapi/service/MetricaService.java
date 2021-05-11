package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.Cupom;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.repository.*;
import br.com.bandtec.osirisapi.views.CupomMaisUsadoView;
import br.com.bandtec.osirisapi.views.RanqueCategoriaView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@AllArgsConstructor
@Service
public class MetricaService {

    private final EventoRepository eventoRepository;
    private final AcessoRepository acessoRepository;
    private final CupomRepository cupomRepository;

    public Integer getUltimaSemana(){

        return acessoRepository.countAcessosSemana();
    }

    public Double getVendasPorAcesso(){

        return (double) acessoRepository.count() / eventoRepository.count();
    }

    public List<RanqueCategoriaView> getRanqueCategoriaView(){

        return eventoRepository.ranque();
    }

    public List<CupomMaisUsadoView> getCupomMaisUsadoView(){

        return eventoRepository.cupomMaisUsado();
    }

    public List<Evento> getComprasPorConsumidor(@PathVariable Integer idConsumidorEcommerce){

        return eventoRepository.findAllByIdConsumidorEcommerce(idConsumidorEcommerce);
    }

    public List<Cupom> getCuponsExpirados(){

        return cupomRepository.findAllByUsadoIsFalseAndDataValidadoLessThanTodayNow();
    }

    public List<Evento> getComprasSemCupom() {

        return eventoRepository.findAllByCupomAndEventoAndUsadoIsFalseAndFkStatus();
    }
}
