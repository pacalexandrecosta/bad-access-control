package pac.owasp.a012021.departamento;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("departamentos")
@RequiredArgsConstructor
public class DepartamentoController {

    private final DepartamentoService service;

    @GetMapping
    public List<Departamento> listar(@RequestParam(required = false) String nome) {
        return service.encontrarPorNome(nome);
    }

    @PostMapping
    public DepartamentoDto criar(@RequestBody @Valid CreateDepartamentoDto dto) {
        var departamento = service.criar(dto);
        var departamentoDto = new DepartamentoDto(departamento.getId(), departamento.getNome());
        return departamentoDto;
    }
    
}
