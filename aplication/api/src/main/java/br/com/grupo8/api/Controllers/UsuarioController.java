package br.com.grupo8.api.Controllers;

import br.com.grupo8.api.models.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class UsuarioController {
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private boolean init = false;

    @GetMapping
    public List<Usuario> getUsuarios(){

        return this.usuarios;
    }

    @PostMapping
    public String addUsuarios(@RequestBody Usuario u){
        this.usuarios.add(u);
        return "Cadastrado com sucesso";
    }

    @PutMapping("/{index}")
    public String updateUsuario(@RequestBody Usuario u,
                                @PathVariable int index){
        if(index <= 0 || index > this.usuarios.size()){
            return "Usuario nao encontrado";
        }else{
            this.usuarios.set(index, u);
            return "Usuario Atualizado";
        }
    }

    @DeleteMapping("/{index}")
    public String deleteUsuario(@PathVariable int index){
        if(index <= 0 || index > this.usuarios.size()){
            return "Usuario nao encontrado";
        }else{
            this.usuarios.remove(index);
            return "Usuario deletado";
        }
    }

    @PostMapping("/login")
    public Usuario loginUsuario(@RequestBody String login,
                                @RequestBody String senha){
        Usuario uLogado = null;
        for (Usuario u : this.usuarios) {
            if(u.getLogin().equals(login) && u.getSenha().equals(senha)){
                uLogado = u;
            }
        }

        if (uLogado == null){
            return null;
        }else{
            return uLogado;
        }

    }

}
