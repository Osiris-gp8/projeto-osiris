package br.com.bandtec.osirisapi.service;

import br.com.bandtec.osirisapi.domain.Usuario;
import br.com.bandtec.osirisapi.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String loginUsuario) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByLoginUsuario(loginUsuario);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new UsernameNotFoundException("Dados inválidos!");
    }

}
