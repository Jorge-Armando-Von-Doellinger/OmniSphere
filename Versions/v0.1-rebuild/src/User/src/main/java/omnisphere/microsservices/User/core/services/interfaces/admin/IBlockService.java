package omnisphere.microsservices.User.core.services.interfaces.admin;


import omnisphere.microsservices.User.core.entity.UserBlock;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IBlockService {
    /// Register the block of this userId
    Mono<UserBlock> block(String userId, String reason);
    /// Do a unblock on this userId
    Mono<UserBlock> unblock(String userId, String reason);
    /// Return all blocks of this user (active or not)
    Mono<List<UserBlock>> findAllBlocksByUserId(String userId);
    /// Get all blocks
    Mono<List<UserBlock>> findAll();
    /// Get latest block of this userIf
    Mono<UserBlock> findLastestBlockByUserId(String userId);
    /// Return true if contains a active block in this userId
    Mono<Boolean> containsActive(String userId);
    /// Return true if contains any block (activo or not) in this userId
    Mono<Boolean> contains(String userId);
}
