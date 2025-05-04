package omnisphere.microsservices.User.api.controllers;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.api.requests.SensitiveRequest;
import omnisphere.microsservices.User.application.dto.UserDTO;
import omnisphere.microsservices.User.application.services.interfaces.IAdminService;
import omnisphere.microsservices.User.core.services.interfaces.user.IUserService;
import omnisphere.microsservices.User.core.entity.*;
import omnisphere.microsservices.User.core.entity.history.UserHistory;
import omnisphere.microsservices.User.core.entity.remove_representation.UserRemoved;
import omnisphere.microsservices.User.core.entity.history.UserRemovedHistory;
import omnisphere.microsservices.User.core.entity.remove_representation.UserUpdateRemoved;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/admin-management")
@AllArgsConstructor
public class AdminController {
    private final IAdminService adminService;
    private final IUserService userService;

    @GetMapping
    public Mono<List<User>> getUsers() {
        return adminService.findAll();
    }
    @GetMapping("/history")
    public Mono<List<UserHistory>> getUsersHistory() {
        return adminService.getAllHistory();
    }
    @PutMapping("/{userId}")
    public Mono<User> updateUser(@PathVariable String userId, @RequestBody @NotNull UserDTO dto) {
        return userService.update(userId, dto);
    }
    @PostMapping("/block")
    public Mono<User> blockUser(@RequestBody SensitiveRequest blockRequest) {
        return adminService.block(blockRequest.getUserId().toString(), blockRequest.getReason());
    }
    @PostMapping("/unblock")
    public Mono<User> unblockUser(@RequestBody SensitiveRequest unblockRequest) {
        return adminService.unblock(unblockRequest.getUserId().toString(), unblockRequest.getReason());
    }
    @DeleteMapping
    public Mono<User> deleteUser(@RequestBody SensitiveRequest request) {
        return adminService.delete(request.getUserId().toString(), request.getReason());
    }

    // Deleted users

    @GetMapping("/users-deleted/history")
    public Mono<List<UserRemovedHistory>> getAllUserDeletionHistory() {
        return adminService.getAllUserDeletionHistory();
    }

    @GetMapping("/users-deleted")
    public Mono<List<UserRemoved>> getUsersRemoved() {
        return adminService.getAllUsersDeleted();
    }

    @GetMapping("/users-deleted/updates/{userId}")
    public Mono<List<UserUpdateRemoved> getUsersUpdateDeleted(String userId) {
        return adminService.getUser();
    }

    @GetMapping("/users-deleted/updates")
    public Mono<List<UserUpdateRemoved> getAllUsersUpdateDeleted() {
        return adminService.getAllUsersUpdateDeleted();
    }
}
