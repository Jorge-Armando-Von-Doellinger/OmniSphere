package omnisphere.microsservices.User.application.services.interfaces;

import omnisphere.microsservices.User.application.dto.UserDTO;
import omnisphere.microsservices.User.core.entity.User;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IAdminService {
    // admin func
    Mono<User> block(String userId, String reason);
    Mono<User> unblock(String userId, String reason);
    Mono<User> delete(String userId, String reason);
    Mono<User> findById(String userId);

    Mono<List<User>> findAll();
    Mono<List<User>> findAllBlocked();
    Mono<List<User>> findAllUnblocked();
    Mono<List<User>> findAllNeverBlocked();

    Mono<List<User>> updateHistoryOfUser(String userId);
    Mono<List<User>> deleteHistoryOfUser(String userId);
    Mono<List<User>> getHistoryOfUser(String userId);

    Mono<List<User>> updateHistory();
    Mono<List<User>> deleteHistory();
    Mono<List<User>> getHistory();


}
