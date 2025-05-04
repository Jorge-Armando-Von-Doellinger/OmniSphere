package omnisphere.microsservices.User.application.services.interfaces;

import omnisphere.microsservices.User.core.entity.*;
import omnisphere.microsservices.User.core.entity.history.UserHistory;
import omnisphere.microsservices.User.core.entity.remove_representation.UserRemoved;
import omnisphere.microsservices.User.core.entity.history.UserRemovedHistory;
import omnisphere.microsservices.User.core.entity.UserUpdate;
import omnisphere.microsservices.User.core.entity.remove_representation.UserUpdateRemoved;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IAdminService {
    // admin func
    Mono<User> block(String userId, String reason);
    Mono<User> unblock(String userId, String reason);
    Mono<UserRemoved> getUserDeleted(String userId); // Capturado pelo microsservico de auditoria

    Mono<User> delete(String userId, String reason);
    Mono<List<UserRemoved>> getUserDeletedByEmail(String email); // Possivelmente, o usuario já possuia uma conta e a excluiu!
    Mono<List<UserRemoved>> getUserDeletedByUsername(String username); // Talvez hajam usuarios excluidos com o mesmo username

    Mono<User> findUserByEmail(String email);
    Mono<User> findUserByUsername(String username);

    Mono<List<UserUpdate>> getUserUpdate(String userId);
    Mono<UserRemovedHistory> getUserDeletedHistory(String userId);
    Mono<UserHistory> getHistoryOfUser(String userId);

    Mono<List<User>> findAll();
    Mono<List<User>> findAllBlocked();
    Mono<List<User>> findAllUnblocked();
    Mono<List<User>> findAllNeverBlocked();

    Mono<List<UserUpdateRemoved>> getAllUsersUpdateDeleted();
    Mono<List<UserUpdateRemoved>> getAllUsersUpdateDeletedByUserId(String userId);
    Mono<List<UserUpdate>> getAllUsersUpdate();
    Mono<List<UserRemoved>> getAllUsersDeleted();
    Mono<List<UserRemovedHistory>> getAllUserDeletionHistory();
    Mono<List<UserHistory>> getAllHistory();
}
