package omnisphere.microsservices.User.core.services.interfaces.admin;

import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.UserUpdate;
import omnisphere.microsservices.User.core.services.interfaces.base.IBaseAdminUserService;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IAdminService extends IBaseAdminUserService<User> {
    Mono<User> delete(String userId, String reason);

    Mono<List<User>> findAll();
    Mono<List<UserUpdate>> findUpdatesByUserId(String userId);

    Mono<List<UserUpdate>> findAllUpdatesRemoved();
    Mono<List<UserUpdate>> findUpdatesRemovedByUserId(String userId);
}
