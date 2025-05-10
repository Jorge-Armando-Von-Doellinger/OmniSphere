package omnisphere.microsservices.User.core.services.interfaces.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.UserUpdate;
import omnisphere.microsservices.User.core.services.interfaces.base.IBaseAdminUserService;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IUserManagementService extends IBaseAdminUserService<User> {
    /// Remove user
    Mono<User> delete(String userId, @NotBlank @NotNull String reason);
    Mono<User> findByEmail(String email);

    /// For update password/email, if the user has forgotten
    Mono<User> update(String userId, User user);

    /// Get ACTIVE users - NOT GET A BLOCKED USERS
    Mono<List<User>> findAll();
    /// To locate all updates of this user
    Mono<List<UserUpdate>> findUpdatesByUserId(String userId);
    /// Get all users blocked - NO ACTIVE
    Mono<List<User>> findUsersBlocked();
}
