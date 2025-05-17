package omnisphere.microsservices.User.infrastructure.cache.redis.implementations;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.core.services.interfaces.cache.ICacheService;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Service
@AllArgsConstructor
public class RedisCacheService implements ICacheService {
    private final ReactiveRedisTemplate<String, Object> template;

    @Override
    public Mono<Boolean> delete(String key) {
        return template.delete(key).map(deleted -> deleted > 0);
    }

    @Override
    public <TObject> Mono<Boolean> put(String key, TObject value, Duration duration) {
        return template.opsForValue().set(key, value, duration);
    }

    @Override
    public <TObject> Mono<Boolean> putUntil(String key, TObject value, Instant expireAt) {
        var now = Instant.now();
        if(expireAt.isBefore(now)) {
            return Mono.just(false);
        }
        return template.opsForValue().set(key, value, Duration.between(now, expireAt));
    }

    @Override
    public <TObject> Mono<TObject> get(String key, Class<TObject> clazz) {
        return template.opsForValue().get(key).cast(clazz);
    }
}
