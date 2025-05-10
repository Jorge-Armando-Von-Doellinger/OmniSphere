package omnisphere.microsservices.User.application.services.admin.implementations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.UserUpdate;
import omnisphere.microsservices.User.core.entity.history.UserHistory;
import omnisphere.microsservices.User.core.repository.IUserRepository;
import omnisphere.microsservices.User.core.services.interfaces.admin.IHistoryService;
import omnisphere.microsservices.User.core.services.interfaces.admin.IUserManagementService;
import omnisphere.microsservices.User.core.services.interfaces.user.IUserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
@Service
public class UserManagementService implements IUserManagementService {
    private final IUserRepository userRepository;
    private final IUserService userService;
    private final IHistoryService<UserHistory> historyService;

    @Override
    public Mono<User> delete(String userId, @NotBlank @NotNull String reason) {
        return null;
    }

    @Override
    public Mono<User> update(String userId, User user) {
        var currentUser = userRepository.findById(userId).flatMap(x -> {
            x.update(user.getUsername(), x.getEmail(), x.getPassword());
            return userService.update(x);
        });
        return currentUser;
    }

    @Override
    public Mono<User> findById(String userDeletedId) {
        return userService.findById(userDeletedId);
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Mono<UserHistory> getUserHistory(String userId) {
        return historyService.make(userId);
    }

    @Override
    public Mono<List<User>> getAllUserHistory() {
        return null;
    }

    @Override
    public Mono<List<User>> findAll() {
        return null;
    }

    @Override
    public Mono<List<UserUpdate>> findUpdatesByUserId(String userId) {
        return null;
    }
}
