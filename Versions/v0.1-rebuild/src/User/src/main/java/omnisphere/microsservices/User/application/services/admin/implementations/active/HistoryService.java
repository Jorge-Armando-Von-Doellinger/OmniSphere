package omnisphere.microsservices.User.application.services.admin.implementations.active;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.history.UserHistory;
import omnisphere.microsservices.User.core.repository.IUserBlockRepository;
import omnisphere.microsservices.User.core.repository.IUserRepository;
import omnisphere.microsservices.User.core.repository.IUserUpdateRepository;
import omnisphere.microsservices.User.core.services.interfaces.admin.IHistoryService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Profile("active")
@AllArgsConstructor
public class HistoryService implements IHistoryService<UserHistory> {
    private final IUserRepository userRepository;
    private final IUserUpdateRepository updateRepository;
    private final IUserBlockRepository blockRepository;

    /// Heavy operation! Please, use with care
    @Override
    public Mono<UserHistory> make(String userId) {
        return Mono.zip(userRepository.findById(userId),
                        updateRepository.findByUserId(userId).collectList(),
                        blockRepository.findByUserId(userId).collectList())
                .map(tuple ->
                        new UserHistory(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }
    /// VERY HEAVY operation! Please, use with care...
    /// Make a List of IHistory<User>, for locale all updates, blooks and the current state!
    @Override
    public Flux<UserHistory> make() {
        return userRepository.findAll()
                .flatMap(x -> make(x.getId().toString()));
    }
    /// Heavy operation! Please, use with care
    /// Make a IHistory<User>, for locale all updates, blooks and the current state!
    // needs a ajust
    @Deprecated
    public Mono<UserHistory> make(User user) {
        return Mono.zip(Mono.just(user),
                        updateRepository.findByUserId(user.getId().toString()).collectList(),
                        blockRepository.findByUserId(user.getId().toString()).collectList())
                .map(tuple ->
                        new UserHistory(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }
}

