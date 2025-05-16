package omnisphere.microsservices.User.api.response;

import org.springframework.stereotype.Service;

@Service
public class ErrorResponse {
    public String makeJson(String message, String path) {
        String errorJson = String.format("""
            {
              "status": 400,
              "error": "Bad Request",
              "message": "%s",
              "path": "%s"
            }
            """, message, path);
        return errorJson;
    }
}
