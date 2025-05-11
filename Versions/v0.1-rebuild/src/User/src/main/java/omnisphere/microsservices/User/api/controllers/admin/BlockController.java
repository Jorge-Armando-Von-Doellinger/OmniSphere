package omnisphere.microsservices.User.api.controllers.admin;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.api.requests.SensitiveRequest;
import omnisphere.microsservices.User.core.entity.UserBlock;
import omnisphere.microsservices.User.core.entity.remove_representation.UserBlockRemoved;
import omnisphere.microsservices.User.core.services.interfaces.admin.IBlockService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/admin-management/block")
@AllArgsConstructor
public class BlockController {
    private final IBlockService blockService;

    @PostMapping
    public Mono<UserBlock> blockUser(@RequestBody SensitiveRequest blockRequest) {
        return blockService.block(blockRequest.getUserId().toString(), blockRequest.getReason());
    }

    @PostMapping("/unblock")
    public Mono<UserBlock> unblockUser(@RequestBody SensitiveRequest unblockRequest) {
        return blockService.unblock(unblockRequest.getUserId().toString(), unblockRequest.getReason());
    }

    @GetMapping
    public Flux<UserBlock> getBlocks() {
        return blockService.findAll();
    }
    @GetMapping("/{userId}")
    public Flux<UserBlock> getBlocksByUserId(String userId) {
        return blockService.findAllByUserId(userId);
    }

    @GetMapping("/latest/{userId}")
    public Mono<UserBlockRemoved> findLatest(String userId) {
        return blockService.findLastestByUserId(userId);
    }

    @GetMapping("/contains-active")
    public Mono<Boolean> containsActiveBlock(String userId) {
        return blockService.containsActiveBlock(userId);
    }


    @GetMapping("/contains/{userId}")
    public Mono<UserBlockRemoved> containsBlock(String userId) {
        return blockService.findLastestByUserId(userId);
    }
}
