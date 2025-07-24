package pac.owasp.a012021.usuario;

public record UsuarioDto(Long id, String login) {
    public UsuarioDto(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin());
    }
}
