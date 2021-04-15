package br.com.bandtec.calculometricas.controller;
import br.com.bandtec.calculometricas.model.Usuario;
import br.com.bandtec.calculometricas.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioRepository ur;

    @GetMapping
    public ResponseEntity getUsuario() {
        List<Usuario> usuarios = ur.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(usuarios);
        }
    }

    @PostMapping
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
}
