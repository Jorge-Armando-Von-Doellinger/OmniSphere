package omnisphere.microsservices.KeyAccess.application.services.interfaces;

import reactor.core.publisher.Mono;

public interface IKeyGenerator {
    Mono<String> GenerateRandomTokenAsync();
    String GenerateRandomToken();
}
