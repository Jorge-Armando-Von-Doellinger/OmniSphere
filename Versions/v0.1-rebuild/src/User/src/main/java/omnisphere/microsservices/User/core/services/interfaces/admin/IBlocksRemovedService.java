package omnisphere.microsservices.User.core.services.interfaces.admin;

import omnisphere.microsservices.User.core.entity.remove_representation.UserBlockRemoved;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IBlocksRemovedService {
    Mono<List<UserBlockRemoved>> findAllBlocksRemoved();
    Mono<List<UserBlockRemoved>> findBlocksRemovedByUserId();
}
