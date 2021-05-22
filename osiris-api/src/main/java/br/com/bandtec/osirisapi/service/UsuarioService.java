package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.converter.implementation.UsuarioConverterImplementation;
import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.UsuarioAcessoRequest;
import br.com.bandtec.osirisapi.dto.response.UsuarioResponse;
import br.com.bandtec.osirisapi.repository.UsuarioRepository;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioConverterImplementation usuarioConverter;
    private final UsuarioRepository usuarioRepository;
    private List<UsuarioResponse> sessoes;

    public List<UsuarioResponse> getUsuarios() throws NotFoundException {

        List<UsuarioResponse> usuarios = usuarioConverter.usuarioListToUsuarioResponseList(usuarioRepository.findAll());

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

    public UsuarioResponse atualizarUsuario(int idUsuario ,Usuario usuario) throws NotFoundException{

        Optional<Usuario> usuarioParaAtualizarOptional = usuarioRepository.findById(idUsuario);

        if(!usuarioParaAtualizarOptional.isPresent()){
            throw new NotFoundException("Usuário não existe");
        }

        Usuario usuarioParaAtualizar = usuarioParaAtualizarOptional.get();

        usuarioParaAtualizar.setEcommerce(usuario.getEcommerce());
        usuarioParaAtualizar.setLoginUsuario(usuario.getLoginUsuario());
        usuarioParaAtualizar.setSenha(usuario.getSenha());

        return usuarioConverter.usuarioToUsuarioResponse(usuarioRepository.save(usuarioParaAtualizar));
    }

    public UsuarioResponse logarUsuario(UsuarioAcessoRequest usuarioAcessoRequest) throws NotFoundException {
        String login = usuarioAcessoRequest.getLogin();
        String senha = usuarioAcessoRequest.getSenha();

        for (UsuarioResponse u : this.sessoes) {
            if(u.getLoginUsuario().equals(login)){
                return u;
            }
        }

        Optional<Usuario> usuario = usuarioRepository.findByLoginEqualsAndSenhaEquals( login, senha );
        if (!usuario.isPresent()){
            throw new NotFoundException("Usuário não existe");
        }else {
            UsuarioResponse usuarioLogado = usuarioConverter.usuarioToUsuarioResponse(usuario.get());
            sessoes.add(usuarioLogado);
            return usuarioLogado;
        }
    }

    public void logoffUsuario(Integer idUsuario) throws NotFoundException{
        for (UsuarioResponse usuario : sessoes) {
            if (usuario.getIdUsuario() == idUsuario){
                this.sessoes.remove(usuario);
                return;
            }
        }

        throw new NotFoundException("Usuário não está logado");
    }

}
