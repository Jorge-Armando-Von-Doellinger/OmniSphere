package omnisphere.microsservices.User.api.exceptions;

public class MissingHeadersException extends RuntimeException {
    public MissingHeadersException() {
        super("Missing required headers for the requisition! Please, check your requisition and try again.");
    }
    public MissingHeadersException(String message) {
         super(message);
    }
}
