package omnisphere.microsservices.User.application.services.admin.implementations.active;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.OldUser;
import omnisphere.microsservices.User.core.entity.history.UserHistory;
import omnisphere.microsservices.User.core.exceptions.EntityNotFoundException;
import omnisphere.microsservices.User.core.repository.user.IUserRepository;
import omnisphere.microsservices.User.core.repository.oldUser.IOldUserRepository;
import omnisphere.microsservices.User.core.services.interfaces.admin.IHistoryService;
import omnisphere.microsservices.User.core.services.interfaces.admin.IActiveUserService;
import omnisphere.microsservices.User.core.services.interfaces.cache.ICacheService;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class ActiveUserService implements IActiveUserService {
    private final IUserRepository userRepository;
    private final IOldUserRepository updateRepository;
    private final IHistoryService<UserHistory> historyService;

    private final ICacheService cache;

    private final String ACTIVE_USER_CACHE = "active-users-list";
    private final String OLD_USER_CACHE = "old-users-list";

    @Override
    public Mono<User> delete(String userId, @NotBlank @NotNull String reason) {
        return userRepository.findById(userId)
                .switchIfEmpty(Mono.error(EntityNotFoundException::new))
                .flatMap(u ->
                        userRepository.deleteById(userId)
                        .then(Mono.just(u))
                );
    }

    @Override
    public Mono<User> update(String userId, User user) {
        var currentUser = userRepository.findById(userId).flatMap(x -> {
            x.update(user.getUsername(), x.getEmail(), x.getPassword());
            return userRepository.save(x);
        });
        return currentUser;
    }

    @Override
    public Mono<User> findByUserId(String userDeletedId) {
        return userRepository.findById(userDeletedId);
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public Flux<User> findWhereContainsUsername(String username) {
        return userRepository.findWhereContainsUsername(username);
    }

    @Override
    public Flux<User> findAll() {
        return cache.get(ACTIVE_USER_CACHE, List.class) // lê como List
                .flatMapMany(list -> Flux.fromIterable((List<User>) list)) // converte para Flux<User>
                .switchIfEmpty(
                        userRepository.findAll()
                                .collectList()
                                .flatMap(users -> cache.putUntil(ACTIVE_USER_CACHE, users, Instant.now().plusSeconds(300))
                                        .thenReturn(users)
                                )
                                .flatMapMany(Flux::fromIterable)
                );
    }

    @Override
    public Flux<OldUser> findUpdatesByUserId(String userId) {
        return cache.get(OLD_USER_CACHE, List.class) // lê como List
                .switchIfEmpty(
                        updateRepository.findByUserId(userId)
                                .collectList()
                                .flatMap(users -> cache.putUntil(OLD_USER_CACHE, users, Instant.now().plusSeconds(300))
                                        .thenReturn(users)
                                )
                ).flatMap(x -> Flux.fromIterable(x));
    }
    @Override
    public Flux<User> findUsersBlocked() {
        return userRepository.findAllBlocked();
    }
}
