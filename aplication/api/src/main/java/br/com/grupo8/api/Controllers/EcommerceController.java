package br.com.grupo8.api.Controllers;

import br.com.grupo8.api.models.Ecommerce;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecommercies")
public class EcommerceController {

    @PostMapping
    public String cadastrarEcommerc(@RequestBody Ecommerce ecommerce){
        
    }
}
