package br.com.bandtec.calculometricas.controller;

import br.com.bandtec.calculometricas.model.dao.DominioStatus;
import br.com.bandtec.calculometricas.repository.DominioStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dominioStatus")
public class DominioStatusController {

    @Autowired
    DominioStatusRepository dsr;

    @GetMapping
    public List<DominioStatus> getBuscarTodos() {
        return dsr.findAll();
    }

}
