package omnisphere.microsservices.User.core.services.interfaces.admin.base;

import omnisphere.microsservices.User.core.entity.fields.UserFields;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IBasicUserManagement<TUser extends UserFields, TUserUpdate> {

    Mono<TUser> findByUserId(String identifier);
    /// Locate all when contains a equals username...
    Mono<List<TUser>> findWhereContainsUsername(String username);
    Mono<List<TUser>> findAll();
    Mono<List<TUser>> findPartition(int offset);

    /// To locate all updates of this user
    Mono<List<TUserUpdate>> findUpdatesByUserId(String userId);
    /// Get all users blocked - NO ACTIVE
    Mono<List<TUser>> findUsersBlocked();


}
