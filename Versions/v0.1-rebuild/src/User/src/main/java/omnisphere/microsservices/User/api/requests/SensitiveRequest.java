package omnisphere.microsservices.User.api.requests;

import lombok.Data;

import java.util.UUID;

@Data
public class SensitiveRequest {
    private UUID userId;
    private String reason;
}
