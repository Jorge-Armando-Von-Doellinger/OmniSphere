package omnisphere.microsservices.User.application.services.admin.implementations;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.history.UserHistory;
import omnisphere.microsservices.User.core.repository.IUserBlockRepository;
import omnisphere.microsservices.User.core.repository.IUserRepository;
import omnisphere.microsservices.User.core.repository.IUserUpdateRepository;
import omnisphere.microsservices.User.core.services.interfaces.admin.IHistoryService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.List;

@AllArgsConstructor
@Service
public class HistoryService implements IHistoryService<UserHistory> {
    private final IUserRepository userRepository;
    private final IUserUpdateRepository updateRepository;
    private final IUserBlockRepository blockRepository;

    @Override
    public Mono<UserHistory> make(String userId) {
        return Mono.zip(userRepository.findById(userId),
                        updateRepository.findByUserId(userId).collectList(),
                        blockRepository.findByUserId(userId).collectList())
                .map(tuple ->
                        new UserHistory(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }

    @Override
    public Mono<List<UserHistory>> make() {
        return userRepository.findAll()
                .flatMap(x -> make(x.getId().toString()))
                .collectList();
    }

    @Deprecated
    public Mono<UserHistory> make(User user) {
        return Mono.zip(Mono.just(user),
                        updateRepository.findByUserId(user.getId().toString()).collectList(),
                        blockRepository.findByUserId(user.getId().toString()).collectList())
                .map(tuple ->
                    new UserHistory(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }
}
