package omnisphere.microsservices.User.application.services.admin.implementations.active;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.core.entity.UserBlock;
import omnisphere.microsservices.User.core.exceptions.EntityNotFoundException;
import omnisphere.microsservices.User.core.repository.block.IUserBlockRepository;
import omnisphere.microsservices.User.core.repository.user.IUserRepository;
import omnisphere.microsservices.User.core.services.interfaces.admin.IBlockService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Service
public class BlockService implements IBlockService {
    private final IUserBlockRepository blockRepository;
    private final IUserRepository userRepository;

    // If is empty means that are a new block, else, are a existent block!
    @Override
    public Mono<UserBlock> block(String userId, String reason) {
        return blockRepository.findLatestActiveBlocks(userId)
                .switchIfEmpty(blockRepository.save(make(userId, reason)));
    }

    @Override
    public Mono<UserBlock> unblock(String userId, String reason) {
        return blockRepository.findLatestActiveBlocks(userId)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Cannot do a unblock, because don't have a active block in this userId")))
                .flatMap(block -> {
                    block.setUnblockReason(reason);
                    block.setUnblockedAt(LocalDateTime.now());
                    return blockRepository.save(block);
                });
    }

    @Override
    public Flux<UserBlock> findAllByUserId(String userId) {
        return blockRepository.findByUserId(userId);
    }

    @Override
    public Flux<UserBlock> findAll() {
        return blockRepository.findAll();
    }

    @Override
    public Mono<UserBlock> findLastestByUserId(String userId) {
        return blockRepository.findLatestActiveBlocks(userId);
    }

    @Override
    public Mono<Boolean> containsActiveBlock(String userId) {
        return blockRepository.findLatestActiveBlocks(userId).hasElement();
    }

    @Override
    public Mono<Boolean> contains(String userId) {
        return blockRepository.findByUserId(userId).hasElements();
    }

    private UserBlock make(String userId, String reason){
        var block = new UserBlock();
        block.setBlockReason(reason);
        block.setUserId(UUID.fromString(userId));
        return block;
    }
}
