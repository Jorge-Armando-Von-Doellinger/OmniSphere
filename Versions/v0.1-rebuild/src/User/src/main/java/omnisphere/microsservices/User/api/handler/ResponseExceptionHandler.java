package omnisphere.microsservices.User.api.handler;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.api.exceptions.MissingHeadersException;
import omnisphere.microsservices.User.core.enums.ErrorLevel;
import omnisphere.microsservices.User.core.exceptions.EntityNotFoundException;
import omnisphere.microsservices.User.core.exceptions.InvalidDataException;
import omnisphere.microsservices.User.core.exceptions.UserWithoutAccessException;
import omnisphere.microsservices.User.core.services.interfaces.log.ILogger;
import omnisphere.microsservices.User.infrastructure.exceptions.FailedToSaveToDatabase;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@ControllerAdvice
@AllArgsConstructor
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    private final ILogger logger;

    @ExceptionHandler(InvalidDataException.class)
    public Mono<ResponseEntity<String>> invalidInputHandle(InvalidDataException ex, ServerWebExchange exchange) {
        return getAllRequestDetails(exchange)
                .flatMap(body -> logger.log(body, ErrorLevel.EXPECTED_ERROR, ex))
                .map(x -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage()));
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public Mono<ResponseEntity<String>> invalidInputHandle(EntityNotFoundException ex, ServerWebExchange exchange) {
        return getAllRequestDetails(exchange)
                .flatMap(body -> logger.log(body, ErrorLevel.EXPECTED_ERROR, ex))
                .map(x -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()));
    }

    @ExceptionHandler(UserWithoutAccessException.class)
    public Mono<ResponseEntity<String>> invalidKeyAccessHandle(UserWithoutAccessException ex, ServerWebExchange exchange) {
        return getAllRequestDetails(exchange)
                .flatMap(body -> logger.log(body, ErrorLevel.EXPECTED_ERROR, ex))
                .map(x -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage()));
    }
    @ExceptionHandler(FailedToSaveToDatabase.class)
    public Mono<ResponseEntity<String>> invalidKeyAccessHandle(FailedToSaveToDatabase ex, ServerWebExchange exchange) {
        return getAllRequestDetails(exchange)
                .flatMap(body -> logger.log(body, ErrorLevel.EXPECTED_ERROR, ex))
                .map(x -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage()));
    }

    @ExceptionHandler(MissingHeadersException.class)
    public Mono<ResponseEntity<String>> invalidKeyAccessHandle(MissingHeadersException ex, ServerWebExchange exchange) {
        return getAllRequestDetails(exchange)
                .flatMap(body -> logger.log(body, ErrorLevel.EXPECTED_ERROR, ex))
                .map(x -> ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(ex.getMessage()));
    }

    @ExceptionHandler(Throwable.class)
    public Mono<ResponseEntity<String>> internalErrorHandle(Throwable ex, ServerWebExchange exchange) {
        return getAllRequestDetails(exchange)
                .flatMap(body -> logger.log(body, ErrorLevel.EXPECTED_ERROR, ex))
                .map(x -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error! For more details, see logs"));
    }

    private Mono<String> getAllRequestDetails(ServerWebExchange exchange) {
        StringBuilder requestDetails = new StringBuilder();
        requestDetails.append("URI: ").append(exchange.getRequest().getURI()).append("\n");
        requestDetails.append("Query Params: ").append(exchange.getRequest().getQueryParams()).append("\n");
        requestDetails.append("Headers: ").append(exchange.getRequest().getHeaders()).append("\n");
        requestDetails.append("Cookies: ").append(exchange.getRequest().getCookies()).append("\n");
        return getBody(exchange.getRequest())
                .map(body -> {
                    // Concatenando o corpo da requisição no StringBuilder
                    requestDetails.append("Body: ").append(body).append("\n");
                    return requestDetails.toString();
                });
    }

    private Mono<String> getBody(ServerHttpRequest request) {
        return request.getBody()
                .collectList()
                .map(dataBuffers -> {
                    StringBuilder body = new StringBuilder();
                    for (var dataBuffer : dataBuffers) {
                        try {
                            body.append(StreamUtils.copyToString(dataBuffer.asInputStream(), StandardCharsets.UTF_8));  // Converte para String
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }finally {
                            // Libera o DataBuffer depois de usá-lo
                            DataBufferUtils.release(dataBuffer);
                        }
                    }
                    return body.toString();
                });
    }
}
