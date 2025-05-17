package omnisphere.microsservices.User.api.controllers.admin;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.api.annotations.RequiredAdmin;
import omnisphere.microsservices.User.core.entity.UserBlock;
import omnisphere.microsservices.User.core.services.interfaces.admin.IBlockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/admin-management/block")
@AllArgsConstructor
@RequiredAdmin
public class BlockController {
    private final IBlockService blockService;

    @PostMapping("/{userId}")
    public ResponseEntity<Mono<UserBlock>> blockUser(@PathVariable String userId, @RequestBody String reason) {
        Mono<UserBlock> result = blockService.block(userId, reason);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/unblock/{userId}")
    public ResponseEntity<Mono<UserBlock>> unblockUser(@PathVariable String userId, @RequestBody String reason) {
        Mono<UserBlock> result = blockService.unblock(userId, reason);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Flux<UserBlock>> getBlocks() {
        Flux<UserBlock> result = blockService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Flux<UserBlock>> getBlocksByUserId(@PathVariable String userId) {
        Flux<UserBlock> result = blockService.findAllByUserId(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/latest/{userId}")
    public ResponseEntity<Mono<UserBlock>> findLatest(@PathVariable String userId) {
        Mono<UserBlock> result = blockService.findLastestByUserId(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/contains-active")
    public ResponseEntity<Mono<Boolean>> containsActiveBlock(@PathVariable String userId) {
        Mono<Boolean> result = blockService.containsActiveBlock(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/contains/{userId}")
    public ResponseEntity<Mono<UserBlock>> containsBlock(@PathVariable String userId) {
        Mono<UserBlock> result = blockService.findLastestByUserId(userId);
        return ResponseEntity.ok(result);
    }
}
