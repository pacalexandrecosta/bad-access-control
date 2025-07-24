package pac.owasp.a012021.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pac.owasp.a012021.usuario.Usuario;

@Component("autorizador")
@AllArgsConstructor
public class AutorizadorCustomizado {


    private Long obterUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Usuario) {
            var usuario = (Usuario) authentication.getPrincipal();
            return usuario.getId();
        }
        // Handle cases where user is not authenticated or principal is not of expected type
        return null;
    }

    public Boolean podeVisualizar(Long usuarioConsultadoId) {
        Long usuarioId = obterUsuarioLogado();
        return usuarioId.equals(usuarioConsultadoId);
    }
}
