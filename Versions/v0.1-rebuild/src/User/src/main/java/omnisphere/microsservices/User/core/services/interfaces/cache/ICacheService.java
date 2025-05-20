package omnisphere.microsservices.User.core.services.interfaces.cache;

import org.springframework.core.ParameterizedTypeReference;
import reactor.core.publisher.Mono;
import java.time.Instant;

public interface ICacheService {
    Mono<Boolean> delete (String key);
    <TObject> Mono<Boolean> put (String key, TObject value);
    <TObject> Mono<Boolean> putUntil(String key, TObject value, Instant expireAt);
    <TObject> Mono<TObject> get (String key, ParameterizedTypeReference<TObject> typeReference);
    <TObject> Mono<TObject> getOrPut (String key, Mono<TObject> supplier);
}
