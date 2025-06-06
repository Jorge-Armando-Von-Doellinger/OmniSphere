package omnisphere.microsservices.User.api.controllers.admin;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.api.annotations.RequiredAdmin;
import omnisphere.microsservices.User.core.entity.history.UserRemovedHistory;
import omnisphere.microsservices.User.core.entity.remove_representation.UserRemoved;
import omnisphere.microsservices.User.core.entity.remove_representation.OldUserRemoved;
import omnisphere.microsservices.User.core.services.interfaces.admin.IBlockRemovedService;
import omnisphere.microsservices.User.core.services.interfaces.admin.IHistoryService;
import omnisphere.microsservices.User.core.services.interfaces.admin.IRemovedUserManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/admin-management/users-removed")
@AllArgsConstructor
@RequiredAdmin
public class RemovedUserController {
    private final IRemovedUserManagementService removedUserManagementService;
    private final IBlockRemovedService blockRemovedService;
    private final IHistoryService<UserRemovedHistory> historyService;

    @GetMapping("/history")
    public ResponseEntity<Flux<UserRemovedHistory>> getAllUserDeletionHistory() {
        return ResponseEntity.ok(historyService.make());
    }

    @GetMapping("/removed")
    public ResponseEntity<Mono<List<UserRemoved>>> getUsersRemoved() {
        return ResponseEntity.ok(removedUserManagementService.findAll());
    }

    @GetMapping("/updates/{userId}")
    public ResponseEntity<Mono<List<OldUserRemoved>>> getUsersUpdateDeleted(@PathVariable String userId) {
        return ResponseEntity.ok(removedUserManagementService.findUpdatesByUserId(userId));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<Mono<UserRemovedHistory>> getUserDeletionHistory(@PathVariable String userId) {
        return ResponseEntity.ok(historyService.make(userId));
    }
}
