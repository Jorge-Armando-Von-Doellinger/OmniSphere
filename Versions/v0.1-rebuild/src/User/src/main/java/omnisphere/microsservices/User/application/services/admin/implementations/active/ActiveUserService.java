package omnisphere.microsservices.User.application.services.admin.implementations.active;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.UserUpdate;
import omnisphere.microsservices.User.core.entity.history.UserHistory;
import omnisphere.microsservices.User.core.repository.IUserRepository;
import omnisphere.microsservices.User.core.repository.IUserUpdateRepository;
import omnisphere.microsservices.User.core.services.interfaces.admin.IHistoryService;
import omnisphere.microsservices.User.core.services.interfaces.admin.IActiveUserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class ActiveUserService implements IActiveUserService {
    private final IUserRepository userRepository;
    private final IUserUpdateRepository updateRepository;
    private final IHistoryService<UserHistory> historyService;

    @Override
    public Mono<User> delete(String userId, @NotBlank @NotNull String reason) {
        return null;
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

    ///  Corrigir - modificar repositorio!
    @Override
    public Flux<User> findWhereContainsUsername(String username) {
        return userRepository.findByUsername(username).flux();
    }

    @Override
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Flux<UserUpdate> findUpdatesByUserId(String userId) {
        return updateRepository.findByUserId(userId);
    }
    ///  Pending - modify the repository
    @Override
    public Flux<User> findUsersBlocked() {
        return null;
    }
}
