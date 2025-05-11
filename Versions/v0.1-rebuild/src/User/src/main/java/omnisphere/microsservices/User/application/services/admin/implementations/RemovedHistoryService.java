package omnisphere.microsservices.User.application.services.admin.implementations;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.core.entity.history.UserRemovedHistory;
import omnisphere.microsservices.User.core.entity.remove_representation.UserRemoved;
import omnisphere.microsservices.User.core.repository.*;
import omnisphere.microsservices.User.core.services.interfaces.admin.IHistoryService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.List;

@Profile("removed")
@Service
@AllArgsConstructor
public class RemovedHistoryService implements IHistoryService<UserRemovedHistory> {
    private final IUserRemovedRepository userRepository;
    private final IUserUpdateRemovedRepository updateRepository;
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
    public Mono<List<UserRemovedHistory>> make() {
        return userRepository.findAll()
                .flatMap(x -> make(x.get().toString()))
                .collectList();
    }
    /// Heavy operation! Please, use with care
    /// Make a IHistory<User>, for locale all updates, blooks and the current state!
    @Deprecated
    public Mono<UserRemovedHistory> make(UserRemoved user) {
        return Mono.zip(Mono.just(user),
                        updateRepository.findByUserId(user.getDeleteId().toString()).collectList(),
                        blockRepository.findByUserId(user.getDeleteId().toString()).collectList())
                .map(tuple ->
                        new UserRemovedHistory(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }
}



