package omnisphere.microsservices.User.core.exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException() {
        super("Invalid data! Please, check the fields and try again");
    }
    public InvalidDataException(String message) {
        super(message);
    }
}
