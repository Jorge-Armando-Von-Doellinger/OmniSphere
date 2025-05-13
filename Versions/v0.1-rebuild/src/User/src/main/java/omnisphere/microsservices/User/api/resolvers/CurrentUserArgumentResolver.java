package omnisphere.microsservices.User.api.resolvers;

import omnisphere.microsservices.User.api.annotations.CurrentUser;
import omnisphere.microsservices.User.api.exceptions.MissingHeadersException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class)
                && parameter.getParameterType().equals(String.class);
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter parameter,
                                        BindingContext bindingContext,
                                        ServerWebExchange exchange) {
        String userId = exchange.getRequest()
                .getHeaders()
                .getFirst("X-USER-IDENTIFIER-STRINGVALUE");
        if (userId == null || userId.isBlank()) {
            return Mono.error(new MissingHeadersException());
        }
        return Mono.just(userId);
    }
}
