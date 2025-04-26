package omnisphere.microsservices.KeyAccess.api.handlers;

import omnisphere.microsservices.KeyAccess.api.exception.InvalidInputException;
import omnisphere.microsservices.KeyAccess.core.exceptions.InvalidKeyAccessException;
import omnisphere.microsservices.KeyAccess.core.exceptions.KeyAccessNotFoundException;
import omnisphere.microsservices.KeyAccess.infrastructure.exception.FailedSaveEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> invalidInputHandle(InvalidInputException ex) {
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidKeyAccessException.class)
    public ResponseEntity<String> invalidKeyAccessHandle(InvalidKeyAccessException ex) {
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(ex.getMessage());
    }
    @ExceptionHandler(KeyAccessNotFoundException.class)
    public ResponseEntity<String> invalidKeyAccessHandle(KeyAccessNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(FailedSaveEntityException.class)
    public ResponseEntity<String> invalidKeyAccessHandle(FailedSaveEntityException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error! Please, check your input and try again.");
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> internalErrorHandle(Throwable ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error! For more details, see logs");
    }
}

