package pac.owasp.a012021.contracheque;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pac.owasp.a012021.usuario.Usuario;

import java.util.List;

@Service
@AllArgsConstructor
public class ContrachequeService {

    private final ContrachequeRepository contrachequeRepository;

    public Contracheque inserir(Long usuarioId, ContrachequeCreateDto contrachequeCreateDto) {
        var contracheque = new Contracheque();
        var usuario = new Usuario();
        usuario.setId(usuarioId);
        contracheque.setUsuario(usuario);
        contracheque.setCompetencia(contrachequeCreateDto.competencia());
        contracheque.setValor(contrachequeCreateDto.valor());
        contracheque = contrachequeRepository.save(contracheque);
        return contracheque;

    }

    public Contracheque obterContracheque(Long id) {
        return contrachequeRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contracheque nao encontrado"));
    }

    public List<Contracheque> obterContracheques(Long userId) {
        return contrachequeRepository.findByUsuarioId(userId);
    }

    public List<Contracheque> obterContracheques(String login) {
        return contrachequeRepository.findByUsuarioLogin(login);
    }
}
