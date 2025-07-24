package pac.owasp.a012021.usuario;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public List<Usuario> obter() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obter(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> obter(String login) {
        return usuarioRepository.findByLogin(login);
    }

    public Usuario inserir(UsuarioCreateDto dto) {
        var usuario = new Usuario();
        usuario.setLogin(dto.login());
        usuario.setPassword(passwordEncoder.encode(dto.password()));
        usuario = usuarioRepository.save(usuario);
        return usuario;
    }

    public void login(LoginRequestDto loginRequestDto) {
        var usuario = usuarioRepository
                .findByLogin(loginRequestDto.login())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));


        if (!passwordEncoder.matches(loginRequestDto.password(), usuario.getPassword())) {
            throw new AccessDeniedException("Usuário/senha incorretos");
        }
    }

    public Usuario obterUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Usuario) {
            var usuario = (Usuario) authentication.getPrincipal();
            return usuario;
        }
        return null;
    }
}
