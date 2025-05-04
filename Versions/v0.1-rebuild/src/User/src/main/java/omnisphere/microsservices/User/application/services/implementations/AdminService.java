package omnisphere.microsservices.User.application.services.implementations;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.application.exception.UserNotFoundException;
import omnisphere.microsservices.User.application.services.interfaces.IAdminService;
import omnisphere.microsservices.User.core.services.interfaces.user.IUserService;
import omnisphere.microsservices.User.core.entity.*;
import omnisphere.microsservices.User.core.entity.UserBlock;
import omnisphere.microsservices.User.core.entity.history.UserHistory;
import omnisphere.microsservices.User.core.entity.remove_representation.UserRemoved;
import omnisphere.microsservices.User.core.entity.history.UserRemovedHistory;
import omnisphere.microsservices.User.core.entity.UserUpdate;
import omnisphere.microsservices.User.core.entity.remove_representation.UserUpdateRemoved;
import omnisphere.microsservices.User.core.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminService implements IAdminService {
    private final IUserService userService;
    // existing rep
    private final IUserRepository repository;
    private final IUserBlockRepository blockRepository;
    private final IUserUpdateRepository updateRepository;
    // data deleted
    private final IUserDeletedRepository userDeletedRepository;
    private final IUserBlockDeletedRepository blockDeletedRepository;
    private final IUserUpdateDeletedRepository updateDeletedRepository;

    @Override
    @Transactional
    public Mono<User> block(String userId, String reason) {
        return userService.findById(userId)
                .flatMap(user -> {
                        var block = new UserBlock();
                        block.setUserId(user.getId());
                        block.setBlockReason(reason);
                        return blockRepository.save(block).then(Mono.just(user));
                });
    }

    @Override
    @Transactional
    public Mono<User> unblock(String userId, String reason) {
        return blockRepository
                .findLatestActiveBlocks(UUID.fromString(userId))
                .flatMap(x -> {
                    x.setUnblockedAt(LocalDateTime.now());
                    x.setUnblockReason(reason);
                    return blockRepository.save(x)
                            .flatMap(y -> repository.findById(userId));
                })
                .switchIfEmpty(Mono.error(UserNotFoundException::new));
    }

    @Override
    @Transactional
    public Mono<User> delete(String userId, String reason) {
        var entity = userService.findById(userId);
        return repository.deleteById(userId)
                .flatMap(Void -> entity);
        // will be implemented a method for import all data (user and user block) to a .csv and export to Cloud! (LGPD)
    }

    @Override
    public Mono<UserRemoved> getUserDeleted(String userId) {
        return userDeletedRepository.findByUserId(userId);
    }

    @Override
    public Mono<List<UserRemoved>> getUserDeletedByEmail(String email) {
        return userDeletedRepository.findByEmail(email).collectList();
    }

    @Override
    public Mono<List<UserRemoved>> getUserDeletedByUsername(String username) {
        return userDeletedRepository.findByUsername(username).collectList();
    }

    @Override
    public Mono<User> findUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Mono<User> findUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    @Transactional
    public Mono<List<User>> findAll() {
        return repository.findAll().collectList();
    }

    @Override
    @Transactional
    public Mono<List<User>>findAllBlocked() {
       return blockRepository.findDistinctLatestActiveBlocks()
               .map(UserBlock::getUserId)
               .flatMap(x -> repository.findById(x.toString()))
               .collectList();
    }

    @Override
    @Transactional
    public Mono<List<User>> findAllUnblocked() {
        var blockedUsers = blockRepository.findDistinctLatestUnblocks()
                .map(UserBlock::getUserId)
                .flatMap(x -> repository.findById(x.toString()))
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
    public Mono<List<UserUpdateRemoved>> getAllUsersUpdateDeleted() {
        return updateDeletedRepository.findAll().collectList();
    }

    @Override
    public Mono<List<UserUpdate>> getAllUsersUpdate() {
        return null;
    }

    @Override
    public Mono<List<UserRemoved>> getAllUsersDeleted() {
        return null;
    }

    /// Heavy operation! Please, use with care
    @Override
    public Mono<List<UserUpdate>> getUserUpdate(String userId) {
        return updateRepository.findByUserId(userId).collectSortedList();
    }

    /// Heavy operation! Please, use with care
    @Override
    @Transactional
    public Mono<UserRemovedHistory> getUserDeletedHistory(String userId) {
        return Mono.zip(
                userDeletedRepository.findByUserId(userId),
                updateDeletedRepository.findByUserId(userId).collectList(),
                blockDeletedRepository.findByUserId(userId).collectList()
        ).map(tuple -> {
            var history = new UserRemovedHistory();
            history.setUser(tuple.getT1());
            history.setUpdates(tuple.getT2());
            history.setBlocks(tuple.getT3());
            return history;
        });
    }

    /// Heavy operation! Please, use with care
    @Override
    @Transactional
    public Mono<UserHistory> getHistoryOfUser(String userId) { // HEAVY OPERATION
        return Mono.zip(
                repository.findById(userId),
                updateRepository.findByUserId(userId).collectList(),
                blockRepository.findByUserId(userId).collectList()
        ).map(tuple -> {
            var history = new UserHistory();
            history.setUser(tuple.getT1());
            history.setUpdates(tuple.getT2());
            history.setBlocks(tuple.getT3());
            return history;
        });
    }
    /// Very heavy operation! Please, avoid using it when possible
    @Override
    public Mono<List<UserRemovedHistory>> getAllUserDeletionHistory() { // HEAVY OPERATION
        return repository.findAll()
                .flatMap(user -> getUserDeletedHistory(user.getId().toString()))
                .collectList();
    }
    /// Very heavy operation! Please, avoid using it when possible
    @Override
    public Mono<List<UserHistory>> getAllHistory() {
        return repository.findAll()
                .flatMap(user -> getHistoryOfUser(user.getId().toString()))
                .collectList();
    }

}
