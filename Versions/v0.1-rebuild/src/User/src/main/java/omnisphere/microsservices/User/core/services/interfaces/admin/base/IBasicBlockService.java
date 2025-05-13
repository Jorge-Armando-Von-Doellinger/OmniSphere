package omnisphere.microsservices.User.core.services.interfaces.admin.base;

import omnisphere.microsservices.User.core.entity.fields.BlockFields;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBasicBlockService<TBlock extends BlockFields> {
    /// Return all blocks of this user (active or not)
    Flux<TBlock> findAllByUserId(String userId);
    /// Get all blocks
    Flux<TBlock> findAll();
    /// Get latest block of this userIf
    Mono<TBlock> findLastestByUserId(String userId);
    /// Return true if contains a active block in this userId
    Mono<Boolean> containsActiveBlock(String userId);
    /// Return true if contains any block (activo or not) in this userId
    Mono<Boolean> contains(String userId);
}
