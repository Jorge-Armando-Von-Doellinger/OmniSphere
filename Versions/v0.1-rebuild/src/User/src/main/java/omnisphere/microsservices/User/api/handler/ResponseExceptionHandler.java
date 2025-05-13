package omnisphere.microsservices.User.api.handler;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.api.exceptions.MissingHeadersException;
import omnisphere.microsservices.User.application.exception.PartialInputException;
import omnisphere.microsservices.User.core.enums.ErrorLevel;
import omnisphere.microsservices.User.core.exceptions.EntityNotFoundException;
import omnisphere.microsservices.User.core.exceptions.InvalidDataException;
import omnisphere.microsservices.User.core.exceptions.UserWithoutAccessException;
import omnisphere.microsservices.User.core.services.interfaces.log.ILogger;
import omnisphere.microsservices.User.infrastructure.exceptions.FailedToSaveToDatabase;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
@AllArgsConstructor
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private final ILogger logger;

    @ExceptionHandler(InvalidDataException.class)
    public Mono<ResponseEntity<String>> handleInvalidData(InvalidDataException ex) {
        return logger.log(ex.getMessage(), ErrorLevel.EXPECTED_ERROR, ex)
                .map(x -> ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ex.getMessage()));
    }

    @ExceptionHandler(PartialInputException.class)
    public Mono<ResponseEntity<String>> handlePartialInput(PartialInputException ex) {
        return logger.log(ex.getMessage(), ErrorLevel.EXPECTED_ERROR, ex)
                .map(x -> ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public Mono<ResponseEntity<String>> handleEntityNotFound(EntityNotFoundException ex) {
        return logger.log(ex.getMessage(), ErrorLevel.EXPECTED_ERROR, ex)
                .map(x -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()));
    }

    @ExceptionHandler({MissingHeadersException.class,
            UserWithoutAccessException.class})
    public Mono<ResponseEntity<String>> handleMissingHeaders(MissingHeadersException ex) {
        return logger.log(ex.getMessage(), ErrorLevel.EXPECTED_ERROR, ex)
                .map(x -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage()));
    }

    @ExceptionHandler(Throwable.class)
    public Mono<ResponseEntity<String>> handleUnknownError(RuntimeException ex) {
        return logger.log(ex.getMessage(), ErrorLevel.UNEXPECTED_ERROR, ex)
                .map(x -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Internal server error! For more details, see logs"));
    }
}