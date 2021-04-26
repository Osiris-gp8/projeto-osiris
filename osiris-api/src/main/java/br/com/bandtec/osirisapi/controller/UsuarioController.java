package br.com.bandtec.osirisapi.controller;
import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioRepository ur;
    private List<Usuario> sessoes;

    @GetMapping
    public ResponseEntity getUsuario() {
        List<Usuario> usuarios = ur.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(usuarios);
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity postUsuario(@RequestBody Usuario novoUsuario) {
        ur.save(novoUsuario);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuario(@PathVariable int idUsuario) {
        if (ur.existsById(idUsuario)) {
            ur.deleteById(idUsuario);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity loginUsuario(@RequestBody String login,
                                @RequestBody String senha){
        Usuario uLogado = null;
        for (Usuario u : this.sessoes) {
            if(u.getLogin().equals(login) && u.getSenha().equals(senha)){
                uLogado = u;
                return ResponseEntity.status(400).body("Usuário já logado");
            }
        }
        //TODO adicionar verificação
        return ResponseEntity.status(200).build();
    }
}
