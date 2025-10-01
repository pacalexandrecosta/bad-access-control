package pac.owasp.a012021.departamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long>, DepartamentoRepositoryCustom {


}
