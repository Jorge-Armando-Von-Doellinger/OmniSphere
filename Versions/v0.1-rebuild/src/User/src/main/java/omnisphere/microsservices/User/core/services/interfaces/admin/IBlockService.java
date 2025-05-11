package omnisphere.microsservices.User.core.services.interfaces.admin;


import omnisphere.microsservices.User.core.entity.UserBlock;
import omnisphere.microsservices.User.core.services.interfaces.admin.base.IBasicBlockService;
import reactor.core.publisher.Mono;

public interface IBlockService extends IBasicBlockService<UserBlock> {
    /// Register the block of this userId
    Mono<UserBlock> block(String userId, String reason);
    /// Do a unblock on this userId
    Mono<UserBlock> unblock(String userId, String reason);

}
