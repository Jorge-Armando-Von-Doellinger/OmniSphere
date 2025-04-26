package omnisphere.microsservices.User.api.handler;

import jakarta.servlet.http.HttpServletRequest;
import omnisphere.microsservices.User.api.annotations.CurrentAdmin;
import omnisphere.microsservices.User.api.annotations.CurrentUser;
import omnisphere.microsservices.User.api.exceptions.MissingHeadersException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CurrentAdminArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(CurrentAdmin.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String adminId = request.getHeader("X-ADMIN-IDENTIFIER-STRINGVALUE"); // Middleware guarantees this headers!
        if(adminId == null || adminId.isBlank()) {
            throw new MissingHeadersException();
        }
        return adminId;
    }
}
