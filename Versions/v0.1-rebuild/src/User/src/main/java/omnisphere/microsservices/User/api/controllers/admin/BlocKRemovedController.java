package omnisphere.microsservices.User.api.controllers.admin;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.core.entity.UserBlock;
import omnisphere.microsservices.User.core.entity.remove_representation.UserBlockRemoved;
import omnisphere.microsservices.User.core.services.interfaces.admin.IBlockRemovedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/admin-management/block-removed")
@AllArgsConstructor
public class BlocKRemovedController {
    private final IBlockRemovedService blockRemovedService;
    public ResponseEntity<Flux<UserBlockRemoved>> getBlocks() {
        return ResponseEntity.ok(blockRemovedService.findAll());
    }

    @GetMapping("/latest/{userId}")
    public ResponseEntity<Mono<UserBlockRemoved>> findLatest(@PathVariable String userId) {
        return ResponseEntity.ok(blockRemovedService.findLastestByUserId(userId));
    }

    @GetMapping("/contains-active/{userId}")
    public ResponseEntity<Mono<Boolean>> containsActiveBlock(@PathVariable String userId) {
        return ResponseEntity.ok(blockRemovedService.containsActiveBlock(userId));
    }

    @GetMapping("/contains/{userId}")
    public ResponseEntity<Mono<UserBlockRemoved>> containsBlock(@PathVariable String userId) {
        return ResponseEntity.ok(blockRemovedService.findLastestByUserId(userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Flux<UserBlockRemoved>> getBlocksByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(blockRemovedService.findAllByUserId(userId));
    }
}
