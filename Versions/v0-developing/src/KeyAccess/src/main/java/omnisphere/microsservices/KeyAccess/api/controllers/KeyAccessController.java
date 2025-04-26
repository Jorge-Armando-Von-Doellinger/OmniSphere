package omnisphere.microsservices.KeyAccess.api.controllers;

import omnisphere.microsservices.KeyAccess.api.exception.InvalidInputException;
import omnisphere.microsservices.KeyAccess.application.model.KeyAccessModel;
import omnisphere.microsservices.KeyAccess.application.services.interfaces.IKeyAccessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/keyaccess")
public class KeyAccessController {
    private final IKeyAccessService service;
    private static final Logger logger = LoggerFactory.getLogger(KeyAccessController.class);
    public KeyAccessController(IKeyAccessService service) {
        this.service = service;
    }
    @PostMapping
    public Mono<String> generate(@RequestBody Date expireOn,
                                 @RequestHeader("X-User-Identifier-StringValue") String userId) {
        if(expireOn.before(new Date())) throw new InvalidInputException("This token cannot expire before the current time!");
        var model = new KeyAccessModel(expireOn, userId);
        return service
                .generate(model)
                .map(ka -> ka.getKey());
    }
    @PostMapping("/validate")
    public Mono<Boolean> validate(@RequestBody String key,
                                  @RequestHeader("X-User-Identifier-StringValue") String userId) {
        var current = service.get(key, userId); // retornará um erro caso null ou invalido!
        return current.hasElement();
    }
    @PostMapping("/headers/validate")
    public Mono<Boolean> validateHeader(@RequestHeader("X-KeyAccess-StringValue") String key,
                                  @RequestHeader("X-User-Identifier-StringValue") String userId) { // rule of this microsservices!
        var current = service.get(key, userId); // retornará um erro caso null ou invalido!
        return current.hasElement();
    }
}
