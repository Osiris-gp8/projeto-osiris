package br.com.bandtec.calculometricas.controller;

import br.com.bandtec.calculometricas.model.dao.Evento;
import br.com.bandtec.calculometricas.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    EventoRepository er;

    @GetMapping
    public List<Evento> getBuscarTodos() {
        return er.findAll();
    }

}
