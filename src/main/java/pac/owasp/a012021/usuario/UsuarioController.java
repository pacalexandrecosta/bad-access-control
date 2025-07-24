package pac.owasp.a012021.usuario;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pac.owasp.a012021.common.jwt.JwtService;
import pac.owasp.a012021.contracheque.ContrachequeCreateDto;
import pac.owasp.a012021.contracheque.ContrachequeDto;
import pac.owasp.a012021.contracheque.ContrachequeService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {
    private final ContrachequeService contrachequeService;
    private UsuarioService usuarioService;
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        usuarioService.login(loginRequestDto);
        var token = jwtService.gerarToken(loginRequestDto.login());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponseDto> signup(@RequestBody UsuarioCreateDto usuarioCreateDto) {
        var usuario = usuarioService.inserir(usuarioCreateDto);
        var loginRequestDto = new LoginRequestDto(usuarioCreateDto.login(), usuarioCreateDto.password());
        usuarioService.login(loginRequestDto);
        var token = jwtService.gerarToken(loginRequestDto.login());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @GetMapping("/{id}/contracheques")
    public ResponseEntity<List<ContrachequeDto>> obterCheques(@PathVariable Long id) {
        var contracheques = contrachequeService.obterContracheques(id);
        var dtos = contracheques
                .stream().map(ContrachequeDto::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/{id}/contracheques")
    public ResponseEntity<ContrachequeDto> inserirContracheque(@PathVariable Long id, @RequestBody ContrachequeCreateDto contrachequeCreateDto) {

        var contracheque = contrachequeService.inserir(id, contrachequeCreateDto);
        return ResponseEntity.ok(new ContrachequeDto(contracheque));
    }
}
