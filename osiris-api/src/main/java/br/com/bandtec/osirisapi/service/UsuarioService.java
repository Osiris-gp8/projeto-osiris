package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.converter.implementation.UsuarioConverterImplementation;
import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.request.UsuarioAcessoRequest;
import br.com.bandtec.osirisapi.dto.response.UsuarioResponse;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.repository.EcommerceRepository;
import br.com.bandtec.osirisapi.repository.UsuarioRepository;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioConverterImplementation usuarioConverter;
    private final UsuarioRepository usuarioRepository;
    private final EcommerceRepository ecommerceRepository;
    private List<UsuarioResponse> sessoes;

    public List<UsuarioResponse> getUsuarios(){

        List<UsuarioResponse> usuarios = usuarioConverter.usuarioListToUsuarioResponseList(usuarioRepository.findAll());

        if (usuarios.isEmpty()) {
            throw new ApiRequestException("Não existem usuários", HttpStatus.NO_CONTENT);
        }

        return usuarios;
    }

    public UsuarioResponse inserirUsuario(Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        if (!ecommerceRepository.existsById(usuario.getEcommerce().getIdEcommerce())){
            throw new ApiRequestException("Ecommerce não existente", HttpStatus.BAD_REQUEST);
        }
        return usuarioConverter.usuarioToUsuarioResponse(usuarioRepository.save(usuario));
    }

    public void deletarUsuario(int idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new ApiRequestException("Usuário não existe", HttpStatus.NOT_FOUND);
        }
        usuarioRepository.deleteById(idUsuario);
    }

    public UsuarioResponse atualizarUsuario(int idUsuario ,Usuario usuario) {

        Optional<Usuario> usuarioParaAtualizarOptional = usuarioRepository.findById(idUsuario);

        if(!usuarioParaAtualizarOptional.isPresent()){
            throw new ApiRequestException("Usuário não existe", HttpStatus.NOT_FOUND);
        }

        Usuario usuarioParaAtualizar = usuarioParaAtualizarOptional.get();

        usuarioParaAtualizar.setEcommerce(usuario.getEcommerce());
        usuarioParaAtualizar.setLoginUsuario(usuario.getLoginUsuario());
        usuarioParaAtualizar.setSenha(usuario.getSenha());

        return usuarioConverter.usuarioToUsuarioResponse(usuarioRepository.save(usuarioParaAtualizar));
    }

    public UsuarioResponse logarUsuario(UsuarioAcessoRequest usuarioAcessoRequest) {
        String login = usuarioAcessoRequest.getLogin();
        String senha = usuarioAcessoRequest.getSenha();

        for (UsuarioResponse u : this.sessoes) {
            if(u.getLoginUsuario().equals(login)){
                return u;
            }
        }

        Optional<Usuario> usuario = usuarioRepository.findByLoginEqualsAndSenhaEquals( login, senha );
        if (!usuario.isPresent()){
            throw new ApiRequestException("Login ou senha incorreto", HttpStatus.BAD_REQUEST);
        }else {
            UsuarioResponse usuarioLogado = usuarioConverter.usuarioToUsuarioResponse(usuario.get());
            sessoes.add(usuarioLogado);
            return usuarioLogado;
        }
    }

    public void logoffUsuario(Integer idUsuario) {
        for (UsuarioResponse usuario : sessoes) {
            if (usuario.getIdUsuario() == idUsuario){
                this.sessoes.remove(usuario);
                return;
            }
        }

        throw new ApiRequestException("Usuário não está logado", HttpStatus.BAD_REQUEST);
    }

}
