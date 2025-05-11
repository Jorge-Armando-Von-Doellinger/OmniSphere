package omnisphere.microsservices.User.api.controllers.user;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.api.annotations.CurrentUser;
import omnisphere.microsservices.User.application.dto.UserDTO;
import omnisphere.microsservices.User.core.services.interfaces.user.IUserService;
import omnisphere.microsservices.User.core.entity.User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final IUserService useCase;

    @GetMapping
    public Mono<User> findById(@CurrentUser String userId) {
        return useCase.findById(userId);
    }

    @PostMapping
    public Mono<User> create(@RequestBody UserDTO dto) {
        var partialUser = new User(dto.username(), dto.email(), dto.password());
        var user = useCase.create(partialUser);
        return user;
    }

    @PutMapping
    public Mono<User> update(@RequestBody UserDTO dto, @CurrentUser String userId) {
        var partialUser = new User(dto.username(), dto.email(), dto.password());
        var updated = useCase.update(userId, partialUser);
        return updated;
    }
    @DeleteMapping
    public Mono<User> delete(@CurrentUser String userId) {
        return useCase.delete(userId);
    }
}
