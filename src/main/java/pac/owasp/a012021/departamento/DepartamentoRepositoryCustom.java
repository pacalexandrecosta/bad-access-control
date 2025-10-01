package pac.owasp.a012021.departamento;

import java.util.List;

public interface DepartamentoRepositoryCustom {

    List<Departamento> obtemPorNome(String nome);
}
