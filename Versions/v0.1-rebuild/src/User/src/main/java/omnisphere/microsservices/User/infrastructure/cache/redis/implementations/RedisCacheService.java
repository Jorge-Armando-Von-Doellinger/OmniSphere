package omnisphere.microsservices.User.infrastructure.cache.redis.implementations;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.services.interfaces.cache.ICacheService;
import omnisphere.microsservices.User.infrastructure.cache.redis.settings.CacheSettings;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class RedisCacheService implements ICacheService {
    private final ReactiveRedisTemplate<String, Object> template;
    private final CacheSettings settings;
    private final ObjectMapper mapper;

    @Override
    public Mono<Boolean> delete(String key) {
        return template.delete(key).map(deleted -> deleted > 0);
    }

    @Override
    public <TObject> Mono<Boolean> put(String key, TObject value) {
        return template.opsForValue().set(key, value, settings.getDuration());
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
    public <TObject> Mono<TObject> get(String key, ParameterizedTypeReference<TObject> typeRef) {
        return template.opsForValue().get(key)
                .map(obj -> {
                    var javaType = mapper.getTypeFactory().constructType(typeRef.getType());
                    return mapper.convertValue(obj, javaType);
                });
    }

    /// If contains key, return a Mono<TObject>, else, active de supplier and put the value, returning a same data of supplier
    /// OBS: Don't have support to complex types (Mono<>,Flux<>, etc...)
    /// Please, use a sample data
    @Override
    public <TObject> Mono<TObject> getOrPut(String key, Mono<TObject> supplier) {
        Mono<TObject> cachedSupplier = supplier.cache();
        return get(key, new ParameterizedTypeReference<TObject>() {} )
                .switchIfEmpty(cachedSupplier.flatMap(value ->
                        put(key, value).thenReturn(value))
                );
    }
}
