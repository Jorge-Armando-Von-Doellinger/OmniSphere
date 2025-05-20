package omnisphere.microsservices.User.api.controllers.user;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.api.annotations.CurrentUser;
import omnisphere.microsservices.User.application.dto.UserDTO;
import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.services.interfaces.user.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping
    public Mono<ResponseEntity<User>> findById(@CurrentUser String userId) {
        return userService.findById(userId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<User>> create(@RequestBody UserDTO userDto) {
        var user = new User(userDto.username(), userDto.email(), userDto.password());
        return userService.create(user)
                .map(ResponseEntity::ok);
    }

    @PatchMapping
    public Mono<ResponseEntity<User>> update(@RequestBody UserDTO dto, @CurrentUser String userId) {
        var user = new User(dto.username(), dto.email(), dto.password());
        return userService.update(userId, user)
                    .map(ResponseEntity::ok);
    }

    @DeleteMapping
    public Mono<ResponseEntity<User>> delete(@CurrentUser String userId) {
        return userService.delete(userId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
