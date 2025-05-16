package omnisphere.microsservices.User.api.filters;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.api.annotations.RequiredAdmin;
import omnisphere.microsservices.User.api.response.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@AllArgsConstructor
public class RequireAdminFilter implements WebFilter {
    private final RequestMappingHandlerMapping handlerMapping;
    private final ErrorResponse errorResponse;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return handlerMapping.getHandler(exchange)
                .flatMap(handler -> {
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod hm = (HandlerMethod) handler;
                        boolean requireAdmin =
                                hm.getBeanType().isAnnotationPresent(RequiredAdmin.class) ||
                                        hm.hasMethodAnnotation(RequiredAdmin.class);
                        if (requireAdmin) {
                            String adminHeader = exchange.getRequest()
                                    .getHeaders()
                                    .getFirst("X-ADMIN-IDENTIFIER-STRINGVALUE");
                            if (adminHeader == null || adminHeader.isEmpty()) {
                                var bufferFactory = exchange.getResponse().bufferFactory();
                                var err = errorResponse.makeJson("Access denied! Access restrict for admins!", "filter");
                                exchange.getResponse().writeWith(Mono.just(bufferFactory.wrap(err.getBytes(StandardCharsets.UTF_8))));
                                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                                return exchange.getResponse().setComplete();
                            }
                        }
                    }
                    return chain.filter(exchange);
                })
                .switchIfEmpty(chain.filter(exchange));
    }
}
