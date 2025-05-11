package omnisphere.microsservices.User.api.controllers.admin;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.api.requests.SensitiveRequest;
import omnisphere.microsservices.User.core.entity.UserBlock;
import omnisphere.microsservices.User.core.services.interfaces.admin.IBlockService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/admin-management/block")
@AllArgsConstructor
public class BlockController {
    private final IBlockService blockService;

    @PostMapping("/{userId}")
    public Mono<UserBlock> blockUser(@PathVariable String userId, @RequestBody String reason) {
        System.out.println(reason + userId);
        return blockService.block(userId, reason);
    }

    @PostMapping("/unblock/{userId}")
    public Mono<UserBlock> unblockUser(@PathVariable String userId, @RequestBody String reason) {
        return blockService.unblock(userId, reason);
    }

    @GetMapping
    public Flux<UserBlock> getBlocks() {
        return blockService.findAll();
    }
    @GetMapping("/{userId}")
    public Flux<UserBlock> getBlocksByUserId(@PathVariable String userId) {
        return blockService.findAllByUserId(userId);
    }

    @GetMapping("/latest/{userId}")
    public Mono<UserBlock> findLatest(@PathVariable String userId) {
        return blockService.findLastestByUserId(userId);
    }

    @GetMapping("/contains-active")
    public Mono<Boolean> containsActiveBlock(@PathVariable String userId) {
        return blockService.containsActiveBlock(userId);
    }


    @GetMapping("/contains/{userId}")
    public Mono<UserBlock> containsBlock(@PathVariable String userId) {
        return blockService.findLastestByUserId(userId);
    }
}
