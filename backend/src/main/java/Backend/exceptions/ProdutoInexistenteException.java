package Backend.exceptions;

public class ProdutoInexistenteException extends Throwable {
    public ProdutoInexistenteException(String message) {
        super(message);
    }
}
