package omnisphere.microsservices.User.api.controllers;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.api.requests.UserRequest;
import omnisphere.microsservices.User.application.services.interfaces.IUserService;
import omnisphere.microsservices.User.core.entity.User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final IUserService useCase;

    @GetMapping("/{id}")
    public Mono<User> findById(@RequestBody UserRequest request) {
        return useCase.findById(request.getUserId());
    }

    @PutMapping
    public Mono<User> update(@RequestBody UserRequest request) {
        var updated = useCase.update(request.getUserId(),
                request.getModel());
        return updated;
    }
    @DeleteMapping
    public Mono<User> delete(@RequestBody UserRequest request) {
        return useCase.delete(request.getUserId());
    }

}
