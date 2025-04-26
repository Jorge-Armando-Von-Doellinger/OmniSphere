package omnisphere.microsservices.User.api.handler;

import jakarta.servlet.http.HttpServletRequest;
import omnisphere.microsservices.User.api.annotations.CurrentUser;
import omnisphere.microsservices.User.api.exceptions.MissingHeadersException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(CurrentUser.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String authorizationHeader = request.getHeader("X-USER-IDENTIFIER-STRINGVALUE"); // Middleware guarantees this headers!
        if(authorizationHeader == null || authorizationHeader.isBlank()) {
            throw new MissingHeadersException();
        }
        return authorizationHeader;
    }
}
