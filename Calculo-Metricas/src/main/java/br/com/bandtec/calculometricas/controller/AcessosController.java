package br.com.bandtec.calculometricas.controller;

import br.com.bandtec.calculometricas.model.dao.Acessos;
import br.com.bandtec.calculometricas.repository.AcessosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/acessos")
public class AcessosController {

    @Autowired
    AcessosRepository ar;

    @GetMapping
    public List<Acessos> getAcessos() {
        return ar.findAll();
    }

}
