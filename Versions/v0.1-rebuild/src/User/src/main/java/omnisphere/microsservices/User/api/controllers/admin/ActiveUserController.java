package omnisphere.microsservices.User.api.controllers.admin;

import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.api.requests.SensitiveRequest;
import omnisphere.microsservices.User.application.dto.UserDTO;
import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.history.UserHistory;
import omnisphere.microsservices.User.core.services.interfaces.admin.IHistoryService;
import omnisphere.microsservices.User.core.services.interfaces.admin.IActiveUserService;
import omnisphere.microsservices.User.core.services.interfaces.user.IUserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/admin-management")
@AllArgsConstructor
public class ActiveUserController {
    private final IActiveUserService userManagementService;
    private final IHistoryService<UserHistory> activeHistoryService;
    private final IUserService userService;

    @GetMapping
    public Flux<User> getUsers() {
        return userManagementService.findAll();
    }

    @GetMapping("/blocked")
    public Flux<User> getUsersBlocked() {
        return userManagementService.findUsersBlocked();
    }

    @GetMapping("/email/{email}")
    public Mono<User> getUsers(@PathVariable String email) {
        return userManagementService.findByEmail(email);
    }

    @GetMapping("/username/contains/{username}")
    public Flux<User> getUsersByUsername(@PathVariable String username) {
        return userManagementService.findWhereContainsUsername(username);
    }
    /// CAUTION - VERY HEAVY OPERATION | 3 CONSULTS FIND-ALL IN THE SAME SERVICE!
    // Future -> Do a cache service to reduces a operations in database!
    @GetMapping("/history")
    public Flux<UserHistory> makeUsersHistory() {
        return activeHistoryService.make();
    }
    /// HEAVY OPERATION
    @GetMapping("/history/{userId}")
    public Flux<UserHistory> makeUserHistory(@PathVariable String userId) {
        return activeHistoryService.make();
    }
    // Necessario testes em cima!
    @PatchMapping("/{userId}")
    public Mono<User> updateUser(@PathVariable String userId, @RequestBody @NotNull UserDTO dto) {
        var partialUser = new User(dto.username(), dto.email(), dto.password()); // Pior performanace! Nova instancia = mais memoria utilizada!
        return userService.update(userId, partialUser);
    }

    @DeleteMapping("/{userId}")
    public Mono<User> deleteUser(@PathVariable String userId, @RequestBody String reason) {
        return userManagementService.delete(userId, reason);
    }

}
