package pac.owasp.a012021.contracheque;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.YearMonth;

public record ContrachequeCreateDto(
        BigDecimal valor,
        @JsonFormat(pattern = "yyyyMM") YearMonth competencia) {
     

}
