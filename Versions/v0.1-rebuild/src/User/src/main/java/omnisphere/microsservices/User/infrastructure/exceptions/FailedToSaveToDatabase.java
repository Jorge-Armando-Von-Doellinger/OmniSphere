package omnisphere.microsservices.User.infrastructure.exceptions;

public class FailedToSaveToDatabase  extends RuntimeException {
    public FailedToSaveToDatabase() {
        super("Could not save on the database! Please, check your input and try again!.");
    }
    public FailedToSaveToDatabase(String message) {
        super(message);
    }
    public FailedToSaveToDatabase(String message, Throwable ex) {
        super(message, ex);
    }
    public FailedToSaveToDatabase(Throwable ex) {
        super(ex);
    }
}
