package omnisphere.microsservices.User.application.services.implementations;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.application.exception.UserNotFoundException;
import omnisphere.microsservices.User.application.mappers.UserMapper;
import omnisphere.microsservices.User.application.dto.UserDTO;
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
    public Mono<User> create(UserDTO model) {
        var user = mapper.map(model);
        user.setPassword(cryptographyService.encrypt(user.getPassword()));
        return repository.save(user);
    }

    @Override
    @Transactional
    public Mono<User> update(String userId, UserDTO model) {
        return findById(userId)
                .flatMap(u -> {
                    u.update(model.username(), model.email(), model.email());
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
    @Transactional
    public Mono<User> findByCredentials(UserDTO model) {
        return repository
                .findByEmail(model.email())
                .filter(x -> cryptographyService.verify(model.password(), x.getPassword()))
                .switchIfEmpty(Mono.error(UserNotFoundException::new));
    }
}
