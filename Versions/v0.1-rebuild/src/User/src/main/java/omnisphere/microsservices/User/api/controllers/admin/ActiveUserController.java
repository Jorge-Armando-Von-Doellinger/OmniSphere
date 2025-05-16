package omnisphere.microsservices.User.api.controllers.admin;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.api.annotations.CurrentAdmin;
import omnisphere.microsservices.User.api.annotations.RequiredAdmin;
import omnisphere.microsservices.User.application.dto.UserDTO;
import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.history.UserHistory;
import omnisphere.microsservices.User.core.services.interfaces.admin.IHistoryService;
import omnisphere.microsservices.User.core.services.interfaces.admin.IActiveUserService;
import omnisphere.microsservices.User.core.services.interfaces.user.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/admin-management")
@AllArgsConstructor
@RequiredAdmin
public class ActiveUserController {
    private final IActiveUserService userManagementService;
    private final IHistoryService<UserHistory> activeHistoryService;
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<Flux<User>> getUsers() {
        return ResponseEntity.ok(userManagementService.findAll());
    }

    @GetMapping("/blocked")
    public ResponseEntity<Flux<User>> getUsersBlocked() {
        return ResponseEntity.ok(userManagementService.findUsersBlocked());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Mono<User>> getUsers(@PathVariable String email) {
        return ResponseEntity.ok(userManagementService.findByEmail(email));
    }

    @GetMapping("/username/contains/{username}")
    public ResponseEntity<Flux<User>> getUsersByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userManagementService.findWhereContainsUsername(username));
    }
    /// CAUTION - VERY HEAVY OPERATION | 3 CONSULTS FIND-ALL IN THE SAME SERVICE!
    // Future -> Do a cache service to reduces a operations in database!
    @GetMapping("/history")
    public ResponseEntity<Flux<UserHistory>> makeUsersHistory() {
        return ResponseEntity.ok(activeHistoryService.make());
    }
    /// HEAVY OPERATION
    @GetMapping("/history/{userId}")
    public ResponseEntity<Flux<UserHistory>> makeUserHistory(@PathVariable String userId) {
        return ResponseEntity.ok(activeHistoryService.make());
    }
    // Necessario testes em cima!
    @PatchMapping("/{userId}")
    public ResponseEntity<Mono<User>> updateUser(@PathVariable String userId,
                                                 @RequestBody @NotNull UserDTO dto) {
        var partialUser = new User(dto.username(), dto.email(), dto.password()); // Pior performanace! Nova instancia = mais memoria utilizada!
        return ResponseEntity.ok(userService.update(userId, partialUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Mono<User>> deleteUser(@PathVariable String userId,
                                                 @RequestBody String reason) {
        return ResponseEntity.ok(userManagementService.delete(userId, reason));
    }

}
