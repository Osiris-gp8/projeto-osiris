package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.UsuarioAcessoRequest;
import br.com.bandtec.osirisapi.repository.UsuarioRepository;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private List<Usuario> sessoes;

    public List<Usuario> getUsuarios() throws NotFoundException {

        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new NotFoundException("Não existem usuários");
        }

        return usuarios;

    }

    public Usuario inserirUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(int idUsuario) throws BadHttpRequest {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new BadHttpRequest();
        }
        usuarioRepository.deleteById(idUsuario);
    }

    public Usuario atualizarUsuario(int idUsuario ,Usuario usuario) throws NotFoundException{

        Optional<Usuario> usuarioParaAtualizarOptional = usuarioRepository.findById(idUsuario);

        if(!usuarioParaAtualizarOptional.isPresent()){
            throw new NotFoundException("Usuário não existe");
        }

        Usuario usuarioParaAtualizar = usuarioParaAtualizarOptional.get();

        usuarioParaAtualizar.setEcommerce(usuario.getEcommerce());
        usuarioParaAtualizar.setLoginUsuario(usuario.getLoginUsuario());
        usuarioParaAtualizar.setSenha(usuario.getSenha());

        return usuarioRepository.save(usuarioParaAtualizar);
    }

    public Usuario logarUsuario(UsuarioAcessoRequest usuarioAcessoRequest) throws NotFoundException {
        String login = usuarioAcessoRequest.getLogin();
        String senha = usuarioAcessoRequest.getSenha();

        for (Usuario u : this.sessoes) {
            if(u.getLoginUsuario().equals(login) && u.getSenha().equals(senha)){
                return u;
            }
        }

        Optional<Usuario> usuario = usuarioRepository.findByLoginEqualsAndSenhaEquals( login, senha );
        if (!usuario.isPresent()){
            throw new NotFoundException("Usuário não existe");
        }else {
            Usuario usuarioLogado = usuario.get();
            sessoes.add(usuarioLogado);
            return usuarioLogado;
        }
    }

    public void logoffUsuario(Integer idUsuario) throws NotFoundException{
        for (Usuario usuario : sessoes) {
            if (usuario.getIdUsuario() == idUsuario){
                this.sessoes.remove(usuario);
            }
        }

        throw new NotFoundException("Usuário não está logado");
    }

}
