package omnisphere.microsservices.KeyAccess.api.exception;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
    public InvalidInputException() {
        super("Invalid input received! Please, insert a valid value for input and try again!");
    }
}
