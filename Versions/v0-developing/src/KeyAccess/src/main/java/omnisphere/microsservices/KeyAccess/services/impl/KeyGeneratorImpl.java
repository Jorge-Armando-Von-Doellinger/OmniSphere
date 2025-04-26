package omnisphere.microsservices.KeyAccess.services.impl;

import omnisphere.microsservices.KeyAccess.services.interfaces.IKeyGenerator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class KeyGeneratorImpl implements IKeyGenerator {
    @Override
    public Mono<String> GenerateRandomTokenAsync() {
        var token = UUID.randomUUID().toString();
        return Mono.just(token);
    }

    @Override
    public String GenerateRandomToken() {
        return UUID.randomUUID().toString();
    }
}
