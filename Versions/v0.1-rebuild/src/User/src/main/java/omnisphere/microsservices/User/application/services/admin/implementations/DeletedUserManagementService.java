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

import java.util.List;

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
    public Mono<List<UserRemoved>> findWhereContainsUsername(String username) {
        return repository.findByUsername(username).collectList();
    }

    @Override
    public Mono<List<UserRemoved>> findAll() {
        return repository.findAll().collectList();
    }

    @Override
    public Mono<List<UserRemoved>> findPartition(int offset) {
        return null;
    }

    @Override
    public Flux<UserRemoved> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Mono<List<OldUserRemoved>> findUpdatesByUserId(String userId) {
        return updateRepository.findByUserId(userId).collectList();
    }

    @Override
    public Mono<List<UserRemoved>> findUsersBlocked() {
        return repository.findAllBlocked().collectList();
    }
}
