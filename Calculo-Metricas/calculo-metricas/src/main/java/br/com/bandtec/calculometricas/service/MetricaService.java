package br.com.bandtec.calculometricas.service;

import br.com.bandtec.calculometricas.domain.Evento;
import br.com.bandtec.calculometricas.repository.AcessoRepository;
import br.com.bandtec.calculometricas.repository.EventoRepository;
import br.com.bandtec.calculometricas.repository.MetaRepository;
import br.com.bandtec.calculometricas.repository.UsuarioRepository;
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