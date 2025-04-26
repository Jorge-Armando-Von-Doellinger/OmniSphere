package omnisphere.microsservices.User.api.middlewares;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

public class ValidateRequiredHeaders implements HandlerInterceptor {

    public ValidateRequiredHeaders() {}

    private boolean isValidHeader(String header) {
        return header != null && !header.isBlank();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var valid = isValidHeader(request.getHeader("X-USER-IDENTIFIER-STRINGVALUE")) ||
                isValidHeader(request.getHeader("X-ADMIN-IDENTIFIER-STRINGVALUE"));
        if(!valid) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Missing required data to validate!");
            return false;
        }
        return true;
    }
}
