package omnisphere.microsservices.User.core.services.interfaces.admin;


import omnisphere.microsservices.User.core.entity.UserBlock;
import omnisphere.microsservices.User.core.services.interfaces.admin.base.IBasicBlockService;
import reactor.core.publisher.Mono;

public interface IBlockService<TBlock extends UserBlock> extends IBasicBlockService<TBlock> {
    /// Register the block of this userId
    Mono<TBlock> block(String userId, String reason);
    /// Do a unblock on this userId
    Mono<TBlock> unblock(String userId, String reason);

}
