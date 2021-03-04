package br.com.grupo8.api.Controllers;

import br.com.grupo8.api.models.Ecommerce;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ecommercies")
public class EcommerceController {

    private List<Ecommerce> ecommerces = new ArrayList<Ecommerce>();

    @PostMapping
    public String cadastrarEcommerce(@RequestBody Ecommerce ecommerce){
        if(ecommerce.getCnpj() == null || ecommerce.getNome() == null){
            return "Valores inválidos";
        }

        this.ecommerces.add(ecommerce);
        return "Ecommerc cadastrado! :)";
    }

    @GetMapping
    public List<Ecommerce> getEcommerces() {
        return ecommerces;
    }

    @GetMapping("/{idEcommerce}")
    public Ecommerce getEcommerce(@PathVariable Integer idEcommerce) {
        if (ecommerces.size() > idEcommerce){
            return ecommerces.get(idEcommerce);
        }

        return null;
    }

    @DeleteMapping("/{idEcommerce}")
    public String deleteEcommerce(@PathVariable int idEcommerce){
        ecommerces.remove(idEcommerce);
        return "Ecommerce removido com sucesso";
    }

    @PutMapping("/{idEcommerce}")
    public String atualizarEcommerce(@RequestBody Ecommerce ecommerce,
                                     @PathVariable Integer idEcommerce){
        ecommerces.set(idEcommerce, ecommerce);
        return "Ecommerce atualizado com sucesso";
    }
}
