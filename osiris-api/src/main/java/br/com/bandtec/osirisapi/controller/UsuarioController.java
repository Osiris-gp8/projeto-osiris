package br.com.bandtec.osirisapi.controller;
import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.UsuarioAcessoRequest;
import br.com.bandtec.osirisapi.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity postUsuario(@RequestBody @Valid Usuario novoUsuario) {
        if (ur.findByLoginEqualsAndSenhaEquals( novoUsuario.getLogin(), novoUsuario.getSenha() ).isPresent()){
            return ResponseEntity.status(400).body("Usuário já existe");
        }else {
            ur.save(novoUsuario);
            return ResponseEntity.status(201).build();
        }
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

    @PutMapping("/{idUsuario}")
    public ResponseEntity putUsuario(@RequestBody Usuario usuario){
        if(ur.findById(usuario.getIdUsuario()).isPresent()){
            ur.save(usuario);
            return ResponseEntity.status(200).build();
        }else{
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity loginUsuario(@RequestBody UsuarioAcessoRequest request){
        String login = request.getLogin();
        String senha = request.getSenha();

        Usuario uLogado = null;
        for (Usuario u : this.sessoes) {
            if(u.getLogin().equals(login) && u.getSenha().equals(senha)){
                uLogado = u;
                return ResponseEntity.status(400).body("Usuário já logado");
            }
        }

        Optional<Usuario> usuario = ur.findByLoginEqualsAndSenhaEquals( login, senha );
        if (!usuario.isPresent()){
            return ResponseEntity.status(404).build();
        }else {
            Usuario usuarioLogado = usuario.get();
            sessoes.add(usuarioLogado);
            return ResponseEntity.status(200).body( usuarioLogado );
        }
    }

    @GetMapping("/logoff")
    public ResponseEntity logoff( @RequestParam Integer idUsuario ){
        for (Usuario usuario : sessoes) {
            if (usuario.getIdUsuario() == idUsuario){
                this.sessoes.remove(usuario);
                return ResponseEntity.status(200).build();
            }
        }
        return ResponseEntity.status(404).body("Usuário não encontrado logado");
    }
}
