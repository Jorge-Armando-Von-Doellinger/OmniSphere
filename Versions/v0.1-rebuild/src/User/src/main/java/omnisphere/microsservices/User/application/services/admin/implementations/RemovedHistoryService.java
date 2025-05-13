package omnisphere.microsservices.User.application.services.admin.implementations;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.core.entity.history.UserRemovedHistory;
import omnisphere.microsservices.User.core.entity.remove_representation.UserRemoved;
import omnisphere.microsservices.User.core.repository.block.IUserBlockRemovedRepository;
import omnisphere.microsservices.User.core.repository.oldUser.IOldUserRemovedRepository;
import omnisphere.microsservices.User.core.repository.user.IUserRemovedRepository;
import omnisphere.microsservices.User.core.services.interfaces.admin.IHistoryService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class RemovedHistoryService implements IHistoryService<UserRemovedHistory> {
    private final IUserRemovedRepository userRepository;
    private final IOldUserRemovedRepository updateRepository;
    private final IUserBlockRemovedRepository blockRepository;

    /// Heavy operation! Please, use with care
    @Override
    public Mono<UserRemovedHistory> make(String userId) {
        return Mono.zip(userRepository.findById(userId),
                        updateRepository.findByUserId(userId).collectList(),
                        blockRepository.findByUserId(userId).collectList())
                .map(tuple ->
                        new UserRemovedHistory(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }
    /// VERY HEAVY operation! Please, use with care...
    /// Make a List of IHistory<User>, for locale all updates, blooks and the current state!
    @Override
    public Flux<UserRemovedHistory> make() {
        return userRepository.findAll()
                .flatMap(x -> make(x.getUserId().toString()));
    }
    /// Heavy operation! Please, use with care
    /// Make a IHistory<User>, for locale all updates, blooks and the current state!
    @Deprecated
    public Mono<UserRemovedHistory> make(UserRemoved user) {
        return Mono.zip(Mono.just(user),
                        updateRepository.findByUserId(user.getUserId().toString()).collectList(),
                        blockRepository.findByUserId(user.getUserId().toString()).collectList())
                .map(tuple ->
                        new UserRemovedHistory(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }
}



