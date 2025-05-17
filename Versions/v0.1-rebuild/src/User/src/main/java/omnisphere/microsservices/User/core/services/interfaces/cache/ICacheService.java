package omnisphere.microsservices.User.core.services.interfaces.cache;

import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

public interface ICacheService {
    Mono<Boolean> delete (String key);
    <TObject> Mono<Boolean> put (String key, TObject value, Duration duration);
    <TObject> Mono<Boolean> putUntil(String key, TObject value, Instant expireAt);
    <TObject> Mono<TObject> get (String key, Class<TObject> clazz);
}
