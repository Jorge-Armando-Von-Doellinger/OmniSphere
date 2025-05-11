package omnisphere.microsservices.User.core.services.interfaces.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.UserUpdate;
import omnisphere.microsservices.User.core.services.interfaces.admin.base.IBasicUserManagement;
import reactor.core.publisher.Mono;

public interface IActiveUserService extends IBasicUserManagement<User, UserUpdate> {
    /// Locate all when contains a equals email...
    Mono<User> findByEmail(String email);
    /// Remove user
    Mono<User> delete(String userId, @NotBlank @NotNull String reason);
    /// For update password/email, if the user has forgotten
    Mono<User> update(String userId, User user);

}
