package pac.owasp.a012021.contracheque;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pac.owasp.a012021.common.converters.YearMonthConverter;
import pac.owasp.a012021.usuario.Usuario;

import java.math.BigDecimal;
import java.time.YearMonth;

@Entity
@Getter
@Setter
public class Contracheque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    private BigDecimal valor;
    
    @Convert(converter = YearMonthConverter.class)
    @Column(length = 6) // yyyyMM
    private YearMonth competencia;

}
