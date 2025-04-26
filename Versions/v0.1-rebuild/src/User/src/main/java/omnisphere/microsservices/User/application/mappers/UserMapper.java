package omnisphere.microsservices.User.application.mappers;

import omnisphere.microsservices.User.application.dto.UserDTO;
import omnisphere.microsservices.User.core.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User map(UserDTO model) {
        return new User(model.username(),
                model.email(),
                model.password());
    }
}
