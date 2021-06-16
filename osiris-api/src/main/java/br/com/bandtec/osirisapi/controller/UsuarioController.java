package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.request.UsuarioAcessoRequest;
import br.com.bandtec.osirisapi.dto.request.UsuarioAtualizacaoRequest;
import br.com.bandtec.osirisapi.service.UsuarioService;
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
    public ResponseEntity getUsuario() {
        return ResponseEntity.status(200).body(usuarioService.getUsuarios());
    }

    @PostMapping
    public ResponseEntity postUsuario(@RequestBody @Valid Usuario novoUsuario) {
        return ResponseEntity.status(201).body(usuarioService.inserirUsuario(novoUsuario));
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity deleteUsuario(@PathVariable int idUsuario) {
        usuarioService.deletarUsuario(idUsuario);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity putUsuario(
            @PathVariable int idUsuario,
            @RequestBody @Valid UsuarioAtualizacaoRequest usuario) {
        return ResponseEntity.status(201).body(usuarioService.atualizarUsuario(idUsuario ,usuario));
    }

    @GetMapping("/logoff")
    public ResponseEntity logoff( @RequestParam Integer idUsuario ) {
        usuarioService.logoffUsuario(idUsuario);
        return ResponseEntity.status(200).build();
    }
}
