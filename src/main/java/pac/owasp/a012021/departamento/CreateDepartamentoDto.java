package pac.owasp.a012021.departamento;

import jakarta.validation.constraints.NotBlank;

public record CreateDepartamentoDto(@NotBlank String nome) {
}
