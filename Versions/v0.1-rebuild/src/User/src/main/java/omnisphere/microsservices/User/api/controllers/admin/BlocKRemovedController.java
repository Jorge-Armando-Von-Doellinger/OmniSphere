package omnisphere.microsservices.User.api.controllers.admin;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.core.entity.UserBlock;
import omnisphere.microsservices.User.core.entity.remove_representation.UserBlockRemoved;
import omnisphere.microsservices.User.core.services.interfaces.admin.IBlockRemovedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/admin-management/block")
@AllArgsConstructor
public class BlocKRemovedController {
    private final IBlockRemovedService blockRemovedService;
    @GetMapping
    public Flux<UserBlockRemoved> getBlocks() {
        return blockRemovedService.findAll();
    }
    @GetMapping("/latest/{userId}")
    public Mono<UserBlockRemoved> findLatest(String userId) {
        return blockRemovedService.findLastestByUserId(userId);
    }

    @GetMapping("/contains-active")
    public Mono<Boolean> containsActiveBlock(String userId) {
        return blockRemovedService.containsActiveBlock(userId);
    }


    @GetMapping("/contains/{userId}")
    public Mono<UserBlockRemoved> containsBlock(String userId) {
        return blockRemovedService.findLastestByUserId(userId);
    }

    @GetMapping("/{userId}")
    public Flux<UserBlockRemoved> getBlocksByUserId(String userId) {
        return blockRemovedService.findAllByUserId(userId);
    }
}
