package Backend.exceptions;

public class UsuarioInexistenteException extends Throwable {
    public UsuarioInexistenteException(String message) {
        super(message);
    }
}
