package omnisphere.microsservices.User.application.services.admin.implementations.active;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.application.anottations.CachingValue;
import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.OldUser;
import omnisphere.microsservices.User.core.entity.UserBlock;
import omnisphere.microsservices.User.core.entity.history.UserHistory;
import omnisphere.microsservices.User.core.exceptions.EntityNotFoundException;
import omnisphere.microsservices.User.core.repository.user.IUserRepository;
import omnisphere.microsservices.User.core.repository.oldUser.IOldUserRepository;
import omnisphere.microsservices.User.core.services.interfaces.admin.IHistoryService;
import omnisphere.microsservices.User.core.services.interfaces.admin.IActiveUserService;
import omnisphere.microsservices.User.core.services.interfaces.cache.ICacheService;
import org.reactivestreams.Publisher;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
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
    private final String USERNAME_SEARCH_USER_CACHE = "username-searched-users-list";
    private final String USER_BLOCKED_CACHE = "users-blocked-list";

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
    @Cacheable(value = "user",  key = "#id")
    public Mono<User> findByUserId(String userDeletedId) {
        return userRepository.findById(userDeletedId);
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public Mono<List<User>> findWhereContainsUsername(String username) {
        return cache.getOrPut(USERNAME_SEARCH_USER_CACHE, userRepository.findWhereContainsUsername(username).collectList())
                .onErrorResume(e -> Mono.empty());
    }
    @Override
    @Cacheable("users")
    public Mono<List<User>> findAll() {
        return userRepository.findAll().collectList();
    }

    @Override
    @Cacheable(value = "olduser",  key = "#userId")
    public Mono<List<OldUser>> findUpdatesByUserId(String userId) {
        return cache.getOrPut(OLD_USER_CACHE, updateRepository.findByUserId(userId).collectList())
                .onErrorResume(e -> Mono.empty());
    }
    @Override
    @Cacheable(value = "usersblocked",  key = "#id")
    public Mono<List<User>> findUsersBlocked() {
        return cache.getOrPut(USER_BLOCKED_CACHE, userRepository.findAllBlocked().collectList())
                .onErrorResume(e -> Mono.empty());
    }


}
