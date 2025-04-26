package omnisphere.microsservices.User.core.exceptions;

public class UserWithoutAccessException  extends RuntimeException {
    public UserWithoutAccessException() {
        super("This user does not have permission for this action!");
    }
    public UserWithoutAccessException(String message) {
        super(message);
    }
}
