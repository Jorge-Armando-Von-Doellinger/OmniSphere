package omnisphere.microsservices.User.core.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super("This entity is not found!");
    }
    public EntityNotFoundException(String message) {
        super(message);
    }
    public EntityNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
