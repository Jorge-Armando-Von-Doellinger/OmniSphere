package omnisphere.microsservices.User.api.handler;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.core.enums.ErrorLevel;
import omnisphere.microsservices.User.core.services.interfaces.log.ILogger;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@AllArgsConstructor
@Order(-2)
public class ResponseExceptionHandler implements ErrorWebExceptionHandler {

    private final ILogger logger;
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        var response = exchange.getResponse();
        var bufferFactory = response.bufferFactory();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String errorJson = String.format("""
            {
              "status": 400,
              "error": "Bad Request",
              "message": "%s",
              "path": "%s"
            }
            """, ex.getMessage().replace('"', '-'),
                exchange.getRequest().getPath());
        return logger.log(errorJson, ErrorLevel.UNEXPECTED_ERROR, ex)
                .then(response.writeWith(Mono.just(bufferFactory.wrap(errorJson.getBytes(StandardCharsets.UTF_8)))));
    }
}