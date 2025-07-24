package pac.owasp.a012021.contracheque;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContrachequeRepository extends JpaRepository<Contracheque, Long> {


    List<Contracheque> findByUsuarioId(Long id);

    List<Contracheque> findByUsuarioLogin(String login);
}
