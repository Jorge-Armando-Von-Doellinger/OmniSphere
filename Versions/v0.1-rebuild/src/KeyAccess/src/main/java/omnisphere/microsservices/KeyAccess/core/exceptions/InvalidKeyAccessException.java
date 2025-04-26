package omnisphere.microsservices.KeyAccess.core.exceptions;

public class InvalidKeyAccessException extends RuntimeException {
    public InvalidKeyAccessException(String message) {
        super(message);
    }
    public InvalidKeyAccessException() {
        super("Invalid key access! Please, insert a valid key and try again.");
    }
}
