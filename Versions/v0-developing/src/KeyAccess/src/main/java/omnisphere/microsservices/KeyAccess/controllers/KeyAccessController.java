package omnisphere.microsservices.KeyAccess.controllers;

import omnisphere.microsservices.KeyAccess.entity.KeyAccess;
import omnisphere.microsservices.KeyAccess.exceptions.InvalidInputException;
import omnisphere.microsservices.KeyAccess.model.KeyAccessModel;
import omnisphere.microsservices.KeyAccess.services.interfaces.IKeyAccessService;
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
        if(expireOn.before(new Date())) throw new InvalidInputException();
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
                                  @RequestHeader("X-User-Identifier-StringValue") String userId) {
        var current = service.get(key, userId); // retornará um erro caso null ou invalido!
        return current.hasElement();
    }
}
