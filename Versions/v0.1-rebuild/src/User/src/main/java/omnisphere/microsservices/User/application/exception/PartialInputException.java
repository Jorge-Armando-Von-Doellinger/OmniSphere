package omnisphere.microsservices.User.application.exception;

public class PartialInputException extends RuntimeException {
    public PartialInputException() {
        super("Partial input received! Please, check your content and try again!");
    }
    public PartialInputException(String message) {
        super(message);
    }
    public PartialInputException(String message, Throwable ex) {
        super(message, ex);
    }
}
