package pac.owasp.a012021.departamento;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;


    public Optional<Departamento> encontrarPorId(Long id) {
        return departamentoRepository.findById(id);
    }

    public List<Departamento> encontrarPorNome(String nome) {
        return departamentoRepository.obtemPorNome(nome);
    }

    public Departamento criar(CreateDepartamentoDto dto) {
        var departamento = new Departamento();
        departamento.setNome(dto.nome());
        return departamentoRepository.save(departamento);
    }
}
