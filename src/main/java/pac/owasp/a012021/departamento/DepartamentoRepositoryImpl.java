package pac.owasp.a012021.departamento;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartamentoRepositoryImpl implements DepartamentoRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Departamento> obtemPorNome(String nome) {
        String sql = "SELECT  d.* FROM Departamento  d WHERE LOWER(coalesce(d.nome,'')) LIKE '" + nome.toLowerCase() + "'";
        return em.createNativeQuery(sql, Departamento.class)
                .getResultList();
    }
}
