package omnisphere.microsservices.User.application.services.admin.implementations;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.core.entity.remove_representation.UserBlockRemoved;
import omnisphere.microsservices.User.core.repository.IUserBlockRemovedRepository;
import omnisphere.microsservices.User.core.services.interfaces.admin.IBlockRemovedService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Profile("removed")
@AllArgsConstructor
public class BlocksRemovedService implements IBlockRemovedService {
    private final IUserBlockRemovedRepository blockRemovedRepository;

    @Override
    public Flux<UserBlockRemoved> findAllByUserId(String userId) {
        return blockRemovedRepository.findByUserId(userId);
    }

    @Override
    public Flux<UserBlockRemoved> findAll() {
        return blockRemovedRepository.findAll();
    }

    @Override
    public Mono<UserBlockRemoved> findLastestByUserId(String userId) {
        return blockRemovedRepository.findByUserId(userId).last();
    }

    @Override
    public Mono<Boolean> containsActiveBlock(String userId) {
        return blockRemovedRepository.findLatestActiveBlocks(userId).hasElement();
    }

    @Override
    public Mono<Boolean> contains(String userId) {
        return blockRemovedRepository.findByUserId(userId).hasElements();
    }
}
