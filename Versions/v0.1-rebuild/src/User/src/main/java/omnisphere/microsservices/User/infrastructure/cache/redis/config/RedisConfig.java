package omnisphere.microsservices.User.infrastructure.cache.redis.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        var context = RedisSerializationContext
                .<String, Object>newSerializationContext(RedisSerializer.string())
                .value(RedisSerializer.json())
                .build();
        return new ReactiveRedisTemplate<>(factory, context);
    }
}
