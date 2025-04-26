package omnisphere.microsservices.User.infrastructure.exceptions;

public class FailedToSaveToDatabase  extends RuntimeException {
    public FailedToSaveToDatabase() {
        super("Could not connect to the database! Please, check the applied settings.");
    }
    public FailedToSaveToDatabase(String message) {
        super(message);
    }
}
