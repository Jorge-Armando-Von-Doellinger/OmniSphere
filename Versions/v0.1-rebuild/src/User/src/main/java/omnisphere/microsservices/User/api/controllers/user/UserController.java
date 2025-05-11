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
    private final IUserService userService;

    @GetMapping
    public Mono<User> findById(@CurrentUser String userId) {
        return userService.findById(userId);
    }

    @PostMapping
    public Mono<User> create(@RequestBody UserDTO dto) {
        var partialUser = new User(dto.username(), dto.email(), dto.password());
        var user = userService.create(partialUser);
        return user;
    }

    @PatchMapping
    public Mono<User> update(@RequestBody UserDTO dto, @CurrentUser String userId) {
        var partialUser = new User(dto.username(), dto.email(), dto.password());
        var updated = userService.update(userId, partialUser);
        return updated;
    }
    @DeleteMapping
    public Mono<User> delete(@CurrentUser String userId) {
        return userService.delete(userId);
    }
}
