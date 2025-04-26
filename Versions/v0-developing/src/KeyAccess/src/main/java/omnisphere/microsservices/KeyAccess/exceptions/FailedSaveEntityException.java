package omnisphere.microsservices.KeyAccess.exceptions;

public class FailedSaveEntityException extends RuntimeException {
    public FailedSaveEntityException(String message) {
        super(message);
    }
    public FailedSaveEntityException() {
        super("Could not save the entity! Please, check your input and try again!");
    }

}
