package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.repository.AcessoRepository;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import br.com.bandtec.osirisapi.repository.MetaRepository;
import br.com.bandtec.osirisapi.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MetricaService {

    private final EventoRepository eventoRepository;
    private final AcessoRepository acessoRepository;
    private final MetaRepository metaRepository;
    private final UsuarioRepository usuarioRepository;

    public List<Evento> comprasSemCupom(Integer consumidor){
        return eventoRepository.findByConsumidorEcommerceWithoutCupom(consumidor);
    }
}
