package omnisphere.microsservices.User.application.services.admin.implementations;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.core.entity.remove_representation.UserRemoved;
import omnisphere.microsservices.User.core.entity.remove_representation.OldUserRemoved;
import omnisphere.microsservices.User.core.repository.user.IUserRemovedRepository;
import omnisphere.microsservices.User.core.repository.oldUser.IOldUserRemovedRepository;
import omnisphere.microsservices.User.core.services.interfaces.admin.IRemovedUserManagementService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeletedUserManagementService implements IRemovedUserManagementService {
    private final IUserRemovedRepository repository;
    private final IOldUserRemovedRepository updateRepository;

    @Override
    public Mono<UserRemoved> findByUserId(String identifier) {
        return repository.findByUserId(identifier);
    }

    @Override
    public Flux<UserRemoved> findWhereContainsUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Flux<UserRemoved> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<UserRemoved> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Flux<OldUserRemoved> findUpdatesByUserId(String userId) {
        return updateRepository.findByUserId(userId);
    }
    ///  Needs a repair - modify the repository!
    @Override
    public Flux<UserRemoved> findUsersBlocked() {
        return null;
    }
}
