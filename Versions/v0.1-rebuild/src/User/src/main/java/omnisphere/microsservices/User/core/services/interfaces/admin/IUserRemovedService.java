package omnisphere.microsservices.User.core.services.interfaces.admin;

import omnisphere.microsservices.User.core.entity.remove_representation.UserRemoved;
import omnisphere.microsservices.User.core.entity.remove_representation.UserUpdateRemoved;
import omnisphere.microsservices.User.core.services.interfaces.base.IBaseAdminUserService;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IUserRemovedService extends IBaseAdminUserService<UserUpdateRemoved> {
    Mono<List<UserRemoved>> findByEmail(String email);

    Mono<List<UserUpdateRemoved>> findAllUpdatesRemoved();
    Mono<List<UserUpdateRemoved>> findUpdatesRemovedByUserId(String userId);
}
