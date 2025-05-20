package omnisphere.microsservices.User.infrastructure.cache.redis.config;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.infrastructure.cache.redis.settings.CacheSettings;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
@EnableCaching
@AllArgsConstructor
public class CacheConfig {
    private final CacheSettings settings;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        var config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(settings.getDuration())
                .disableCachingNullValues();

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .build();
    }
}
