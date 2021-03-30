package br.com.bandtec.calculometricas.model.dto;

import br.com.bandtec.calculometricas.repository.AcessosRepository;
import br.com.bandtec.calculometricas.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Metrica {
    @Autowired
    AcessosRepository ar;

    @Autowired
    EventoRepository er;

//    public List<String> getAcessoSemanal() {
//        return ar.countAcessosSemana();
//    }

//    public int getVendasAcesso() {
//        int i = er.countEvento() / ar.countAcessos();
//        return i;
//    }

//    public List<String> getRanqueCategoria() {
//        return er.ranqueCategoria();
//    }
}
