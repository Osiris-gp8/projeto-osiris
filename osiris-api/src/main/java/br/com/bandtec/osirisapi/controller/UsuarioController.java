package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.UsuarioAcessoRequest;
import br.com.bandtec.osirisapi.service.UsuarioService;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {


    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity getUsuario() throws NotFoundException {
        return ResponseEntity.status(200).body(usuarioService.getUsuarios());
    }

    @PostMapping
    public ResponseEntity postUsuario(@RequestBody @Valid Usuario novoUsuario) {
        return ResponseEntity.status(201).body(usuarioService.inserirUsuario(novoUsuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuario(@PathVariable int idUsuario) throws BadHttpRequest {
        usuarioService.deletarUsuario(idUsuario);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity putUsuario(
            @PathVariable int idUsuario,
            @RequestBody Usuario usuario) throws NotFoundException {
        return ResponseEntity.status(201).body(usuarioService.atualizarUsuario(idUsuario ,usuario));
    }

    @PostMapping("/login")
    public ResponseEntity loginUsuario(@RequestBody UsuarioAcessoRequest usuarioAcessoRequest) throws NotFoundException {
        return ResponseEntity.status(200).body(usuarioService.logarUsuario(usuarioAcessoRequest));
    }

    @GetMapping("/logoff")
    public ResponseEntity logoff( @RequestParam Integer idUsuario ) throws NotFoundException {
        usuarioService.logoffUsuario(idUsuario);
        return ResponseEntity.status(200).build();
    }
}
