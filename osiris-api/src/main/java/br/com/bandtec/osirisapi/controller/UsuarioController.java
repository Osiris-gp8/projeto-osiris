package br.com.bandtec.osirisapi.controller;

import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.request.NovoUsuarioRequest;
import br.com.bandtec.osirisapi.dto.request.UsuarioAtualizacaoRequest;
import br.com.bandtec.osirisapi.dto.request.UsuarioRecuperarSenhaRequest;
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
            @RequestBody UsuarioAtualizacaoRequest usuario) {
        return ResponseEntity.status(201).body(usuarioService.atualizarUsuario(idUsuario ,usuario));
    }

    @GetMapping("/logoff")
    public ResponseEntity logoff( @RequestParam Integer idUsuario ) {
        usuarioService.logoffUsuario(idUsuario);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/recuperar-senha/solicitacao/{emailUsuario}")
    public ResponseEntity solicitarRecuperarSenha(@PathVariable String emailUsuario){

        usuarioService.solicitacaoRecuperarSenha(emailUsuario);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/recuperar-senha/{token}")
    public ResponseEntity recuperarSenha(
            @PathVariable String token,
            @RequestBody @Valid UsuarioRecuperarSenhaRequest recuperarSenhaRequest){

        return ResponseEntity.status(201).body(usuarioService.recuperarSenha(token, recuperarSenhaRequest));
    }

    @GetMapping("/ecommerce/{idEcommerce}")
    public ResponseEntity getUsuariosPorEcommerce(@PathVariable Integer idEcommerce){

        return ResponseEntity.status(200).body(usuarioService.buscarUsuarioPorEcommerce(idEcommerce));
    }

    @GetMapping("/{login}")
    public ResponseEntity getUsuarioPorLogin(@PathVariable String login){

        return ResponseEntity.status(200).body(usuarioService.findUsuarioPorLogin(login));
    }

    @PostMapping("/colaborador")
    public ResponseEntity postNovoColaborador(@RequestBody @Valid NovoUsuarioRequest novoUsuarioRequest){
        return ResponseEntity.status(201).body(usuarioService.addNovoColaborador(novoUsuarioRequest));
    }
}
