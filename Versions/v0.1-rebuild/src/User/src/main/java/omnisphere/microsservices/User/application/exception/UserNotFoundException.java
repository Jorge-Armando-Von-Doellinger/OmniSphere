package omnisphere.microsservices.User.application.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Could not found user! Check your credentials and try again!");
    }

    public UserNotFoundException(String message)  {
        super(message);
    }
}
