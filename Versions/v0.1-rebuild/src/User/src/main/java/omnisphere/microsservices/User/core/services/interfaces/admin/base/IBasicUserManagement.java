package omnisphere.microsservices.User.core.services.interfaces.admin.base;

import omnisphere.microsservices.User.core.entity.UserFields;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBasicUserManagement<TUser extends UserFields, TUserUpdate> {
    Mono<TUser> findByUserId(String identifier);
    /// Locate all when contains a equals username...
    Flux<TUser> findWhereContainsUsername(String username);
    Flux<TUser> findAll();

    /// To locate all updates of this user
    Flux<TUserUpdate> findUpdatesByUserId(String userId);
    /// Get all users blocked - NO ACTIVE
    Flux<TUser> findUsersBlocked();
}
