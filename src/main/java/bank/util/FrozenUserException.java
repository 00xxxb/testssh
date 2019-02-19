package bank.util;

public class FrozenUserException extends RuntimeException {
    public FrozenUserException(String message) {
        super(message);
    }

    public FrozenUserException() {
    }
}
