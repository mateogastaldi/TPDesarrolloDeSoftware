package exceptions.cliente;

public class ClienteNoCreadoException extends RuntimeException {
    public ClienteNoCreadoException(String message) {
        super(message);
    }
}
