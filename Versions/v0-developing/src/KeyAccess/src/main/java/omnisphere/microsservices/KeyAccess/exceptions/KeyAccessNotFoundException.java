package omnisphere.microsservices.KeyAccess.exceptions;

public class KeyAccessNotFoundException extends RuntimeException {
    public KeyAccessNotFoundException(String message) {
        super(message);
    }
    public KeyAccessNotFoundException() {
        super("Could not possible locate your key access. Please, insert a valid input and try again");
    }
}
