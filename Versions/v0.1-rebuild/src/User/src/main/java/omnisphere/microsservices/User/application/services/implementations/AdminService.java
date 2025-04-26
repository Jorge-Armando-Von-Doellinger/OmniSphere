package omnisphere.microsservices.User.application.services.implementations;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.application.services.interfaces.IAdminService;
import omnisphere.microsservices.User.application.services.interfaces.IUserService;
import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.UserBlock;
import omnisphere.microsservices.User.core.repository.IUserBlockRepository;
import omnisphere.microsservices.User.core.repository.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.ReactiveTransaction;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminService implements IAdminService {
    private final IUserRepository repository;
    private final IUserService userService;
    private final IUserBlockRepository blockRepository;

    @Override
    @Transactional
    public Mono<User> block(String userId, String reason) {
        return userService.findById(userId)
                .flatMap(user -> {
                        var block = new UserBlock();
                        block.setUser(user);
                        block.setBlockReason(reason);
                        return blockRepository.save(block).then(Mono.just(user));
                });
    }

    @Override
    @Transactional
    public Mono<User> unblock(String userId, String reason) {
        return userService.findById(userId)
                .flatMap(user -> {
                    var block = new UserBlock();
                    block.setUser(user);
                    block.setUnblockReason(reason);
                    block.setUnblockedAt(LocalDateTime.now());
                    return blockRepository.save(block).then(Mono.just(user));
                });
    }

    @Override
    @Transactional
    public Mono<User> delete(String userId, String reason) {
        // will be implemented a method for import all data (user and user block) to a .csv and export to Cloud! (LGPD)
        return null;
    }

    @Override
    @Transactional
    public Mono<User> findById(String userId) {
        return userService.findById(userId);
    }

    @Override
    @Transactional
    public Mono<List<User>> findAll() {
        return repository.findAll().collectList(); // ta com erro
    }

    @Override
    @Transactional
    public Mono<List<User>>findAllBlocked() {
       return blockRepository.findDistinctLatestActiveBlocks()
               .map(UserBlock::getUser)
               .collectList();
    }

    @Override
    @Transactional
    public Mono<List<User>> findAllUnblocked() {
        var blockedUsers = blockRepository.findDistinctLatestUnblocks()
                .map(UserBlock::getUser)
                .collectList();
        return blockedUsers;
    }

    @Override
    @Transactional
    public Mono<List<User>> findAllNeverBlocked() {
        return repository.findAllNeverBlocked()
                .collectList();
    }

    @Override
    @Transactional
    public Mono<List<User>> updateHistoryOfUser(String userId) {
        return null;
    }

    @Override
    @Transactional
    public Mono<List<User>> deleteHistoryOfUser(String userId) {
        return null;
    }

    @Override
    @Transactional
    public Mono<List<User>> getHistoryOfUser(String userId) {
        return null;
    }

    @Override
    @Transactional
    public Mono<List<User>> updateHistory() {
        return null;
    }

    @Override
    @Transactional
    public Mono<List<User>> deleteHistory() {
        return null;
    }

    @Override
    @Transactional
    public Mono<List<User>> getHistory() {
        return null;
    }
}
