package omnisphere.microsservices.User.application.services.user;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.application.exception.UserNotFoundException;
import omnisphere.microsservices.User.application.mappers.UserMapper;
import omnisphere.microsservices.User.core.services.interfaces.user.IUserService;
import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.repository.IUserRepository;
import omnisphere.microsservices.User.core.services.interfaces.cryptography.ICryptographyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository repository;
    private final ICryptographyService cryptographyService;
    private final UserMapper mapper;

    @Override
    @Transactional
    public Mono<User> create(User user) {
        user.setPassword(cryptographyService.encrypt(user.getPassword()));
        return repository.save(user);
    }

    @Override
    @Transactional
    public Mono<User> update(String userId, User partialUser) {
        return findById(userId)
                .flatMap(u -> {
                    var password = cryptographyService.encrypt(partialUser.getPassword());
                    u.update(partialUser.getUsername(), partialUser.getEmail(), password);
                    return repository.save(u);
                });
    }

    @Override
    @Transactional
    public Mono<User> delete(String userId) {
        return findById(userId)
                .flatMap(u -> repository
                        .deleteById(userId)
                        .then(Mono.just(u)));
    }

    @Override
    @Transactional
    public Mono<User> findById(String userId) {
        return repository.findById(userId)
                .switchIfEmpty(Mono.error(UserNotFoundException::new));
    }

    @Override
    public Mono<User> validate(String email, String password) {
        return repository.findByEmail(email).map(user ->
                cryptographyService.verify(password, user.getPassword())
                    ? user : null // If is valid, return user. Else, return null!
        ).switchIfEmpty(Mono.error(UserNotFoundException::new));
    }

}
