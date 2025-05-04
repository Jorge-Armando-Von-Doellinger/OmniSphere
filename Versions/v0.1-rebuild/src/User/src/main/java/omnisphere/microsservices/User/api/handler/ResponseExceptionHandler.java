package omnisphere.microsservices.User.api.handler;

import omnisphere.microsservices.User.api.exceptions.MissingHeadersException;
import omnisphere.microsservices.User.application.exception.UserNotFoundException;
import omnisphere.microsservices.User.core.exceptions.InvalidDataException;
import omnisphere.microsservices.User.core.exceptions.UserWithoutAccessException;
import omnisphere.microsservices.User.infrastructure.exceptions.FailedToSaveToDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> invalidInputHandle(InvalidDataException ex) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> invalidInputHandle(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UserWithoutAccessException.class)
    public ResponseEntity<String> invalidKeyAccessHandle(UserWithoutAccessException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
    @ExceptionHandler(FailedToSaveToDatabase.class)
    public ResponseEntity<String> invalidKeyAccessHandle(FailedToSaveToDatabase ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(MissingHeadersException.class)
    public ResponseEntity<String> invalidKeyAccessHandle(MissingHeadersException ex) {
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(ex.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> internalErrorHandle(Throwable ex) {
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error! For more details, see logs");
    }
}
