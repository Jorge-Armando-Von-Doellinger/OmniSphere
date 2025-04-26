package omnisphere.microsservices.KeyAccess.infrastructure.exception;

public class FailedSaveEntityException extends RuntimeException {
    public FailedSaveEntityException(String message) {
        super(message);
    }
    public FailedSaveEntityException() {
        super("Could not save the entity! Please, check your input and try again!");
    }

}
