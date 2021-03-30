package br.com.bandtec.calculometricas.controller;

import br.com.bandtec.calculometricas.model.dao.Cupom;
import br.com.bandtec.calculometricas.repository.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cupom")
public class CupomController {

    @Autowired
    CupomRepository cr;

    @GetMapping
    public List<Cupom> getBuscarTodos() {
        return cr.findAll();
    }

}
