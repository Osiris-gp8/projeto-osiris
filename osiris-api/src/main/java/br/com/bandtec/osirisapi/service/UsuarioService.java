package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.converter.implementation.UsuarioConverterImplementation;
import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.dto.request.UsuarioAcessoRequest;
import br.com.bandtec.osirisapi.dto.request.UsuarioAtualizacaoRequest;
import br.com.bandtec.osirisapi.dto.request.UsuarioRecuperarSenhaRequest;
import br.com.bandtec.osirisapi.dto.response.UsuarioResponse;
import br.com.bandtec.osirisapi.exception.ApiRequestException;
import br.com.bandtec.osirisapi.mensageria.Constants;
import br.com.bandtec.osirisapi.mensageria.EmailConfig;
import br.com.bandtec.osirisapi.repository.EcommerceRepository;
import br.com.bandtec.osirisapi.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioConverterImplementation usuarioConverter;
    private final UsuarioRepository usuarioRepository;
    private final EcommerceRepository ecommerceRepository;
    private final TokenService tokenService;
    private List<UsuarioResponse> sessoes;
    private Constants constants;
    private final UserInfo userInfo;


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

    public UsuarioResponse atualizarUsuario(int idUsuario , UsuarioAtualizacaoRequest usuario) {

        UsuarioResponse usuarioInfo = userInfo.getUsuario();

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);

        if(!optionalUsuario.isPresent()){
            throw new ApiRequestException("Usuário não existe", HttpStatus.NOT_FOUND);
        }

        if (usuarioInfo.getIdUsuario() != idUsuario){
            throw new ApiRequestException("Não é possível alterar outro usuário", HttpStatus.UNAUTHORIZED);
        }

        Usuario usuarioParaAtualizar = optionalUsuario.get();

        if (!usuario.getLoginUsuario().isEmpty()){
            usuarioParaAtualizar.setLoginUsuario(usuario.getLoginUsuario());
        }

        if (!usuario.getNomeCompleto().isEmpty()) {
            usuarioParaAtualizar.setNomeCompleto(usuario.getNomeCompleto());
        }

        return usuarioConverter.usuarioToUsuarioResponse(usuarioRepository.saveAndFlush(usuarioParaAtualizar));
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

    public boolean solicitacaoRecuperarSenha(String emailUsuario) {

        EmailConfig emailConfig = new EmailConfig();

        if (!validarEmailUsuario(emailUsuario)){

            throw new ApiRequestException("E-mail não encontrado na base ou incorreto", HttpStatus.BAD_REQUEST);
        }

        String token = tokenService.gerarTokenAssinado(emailUsuario);
        String mensagem = gerarMesagemResetDeSenha(emailUsuario, token);

        boolean enviado = emailConfig.enviarEmail(
                mensagem,
                constants.ASSUNTO_RECUPERAR_SENHA,
                emailUsuario);

        if (enviado){

            return true;
        }else {

            throw new ApiRequestException("E-mail de recuperação de senha não foi enviado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String gerarMesagemResetDeSenha(String emailUsuario, String token){

        String mensagem =
                String.format(constants.MENSAGEM_RECUPERAR_SENHA,
                        emailUsuario, token);

        return mensagem;
    }

    private boolean validarEmailUsuario(String email){

        Optional<Usuario> optionalUsuario = usuarioRepository.findByLoginUsuario(email);

        return optionalUsuario.isPresent() ? true : false;
    }

    public UsuarioResponse recuperarSenha(String token, UsuarioRecuperarSenhaRequest recuperarSenhaRequest) {

        if (tokenService.isTokenValido(token)){
            String email = tokenService.getUrlAssinadaViaToken(token);
            Usuario usuario = usuarioRepository.findByLoginUsuario(email).get();

            usuario.setSenha(new BCryptPasswordEncoder().encode(recuperarSenhaRequest.getSenha()));

            return usuarioConverter.usuarioToUsuarioResponse(usuarioRepository.saveAndFlush(usuario));

        } else {
            throw new ApiRequestException("Token expirado", HttpStatus.FORBIDDEN);
        }

    }

    public List<UsuarioResponse> buscarUsuarioPorEcommerce(Integer idEcommerce){

        UsuarioResponse usuarioInfo = userInfo.getUsuario();

        if (!ecommerceRepository.existsById(idEcommerce)){
            throw new ApiRequestException("", HttpStatus.BAD_REQUEST);
        }

        if (usuarioInfo.getEcommerce().getIdEcommerce() != idEcommerce){
            throw new ApiRequestException("", HttpStatus.UNAUTHORIZED);
        }

        List<Usuario> usuario = usuarioRepository.findAllByEcommerceIdEcommerce(idEcommerce);

        return usuarioConverter.usuarioListToUsuarioResponseList(usuario);
    }

    public UsuarioResponse findUsuarioPorLogin(String login){

        Optional<Usuario> optionalUsuario = usuarioRepository.findByLoginUsuario(login);

        if (!optionalUsuario.isPresent()){
            throw new ApiRequestException("Usuário não encontrado.", HttpStatus.BAD_REQUEST);
        }

        return usuarioConverter.usuarioToUsuarioResponse(optionalUsuario.get());
    }
}
