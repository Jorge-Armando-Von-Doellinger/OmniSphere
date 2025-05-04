package omnisphere.microsservices.User.core.services.interfaces.admin;


import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.UserBlock;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IBlockService {
    Mono<User> block(String userId, String reason);
    Mono<User> unblock(String userId, String reason);

    Mono<List<UserBlock>> findBlocksByUserId(String userId);
    Mono<List<UserBlock>> findBlocks();

    Mono<UserBlock> findLastBlockByUserId(String userId);

    Mono<Boolean> isBlocked(String userId);
    // findAllBlocked, findAllUnblocked, findAllNeverBlocked, findAllBlocks(userId)
}
