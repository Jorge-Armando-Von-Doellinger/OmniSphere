package omnisphere.microsservices.User.api.requests;

import lombok.Data;
import omnisphere.microsservices.User.api.annotations.CurrentUser;
import omnisphere.microsservices.User.application.dto.UserDTO;

@Data
public class UserRequest {
    private UserDTO model;

    @CurrentUser
    private String userId;
}
