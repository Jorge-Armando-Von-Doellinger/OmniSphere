package omnisphere.microsservices.User.api.middlewares;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class ValidateRequiredHeadersFilter implements WebFilter {

    private boolean isValidHeader(String header) {
        return header != null && !header.isBlank();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        var headers = exchange.getRequest().getHeaders();

        boolean hasUserHeader = isValidHeader(headers.getFirst("X-USER-IDENTIFIER-STRINGVALUE"));
        boolean hasAdminHeader = isValidHeader(headers.getFirst("X-ADMIN-IDENTIFIER-STRINGVALUE"));

        if (!hasUserHeader && !hasAdminHeader) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            var buffer = exchange.getResponse()
                    .bufferFactory()
                    .wrap("Unauthorized: Missing required data to validate!".getBytes());

            return exchange.getResponse().writeWith(Mono.just(buffer));
        }

        return chain.filter(exchange);
    }
}
