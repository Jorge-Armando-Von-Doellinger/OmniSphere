package omnisphere.microsservices.User.core.services.interfaces.base;

import omnisphere.microsservices.User.core.entity.history.UserRemovedHistory;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IBaseAdminUserService<TUser> {
    Mono<TUser> findById(String userDeletedId);
    Mono<List<TUser>> findByEmail(String email);
    Mono<TUser> findByUsername(String username);

    /// Heavy operation! Please, use with care
    Mono<TUser> getUserHistory(String userId);

    /// Very heavy operation! Please, avoid using it when possible
    Mono<List<TUser>> getAllUserHistory();
    Mono<List<TUser>> findAll();
}
