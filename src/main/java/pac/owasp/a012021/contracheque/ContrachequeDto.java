package pac.owasp.a012021.contracheque;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.YearMonth;

public record ContrachequeDto(Long id, Long usuario,
                              BigDecimal valor,
                              @JsonFormat(pattern = "yyyyMM") YearMonth competencia) {
    public ContrachequeDto(Contracheque contracheque) {
        this(contracheque.getId()
                , contracheque.getUsuario().getId()
                , contracheque.getValor()
                , contracheque.getCompetencia());
    }

}
