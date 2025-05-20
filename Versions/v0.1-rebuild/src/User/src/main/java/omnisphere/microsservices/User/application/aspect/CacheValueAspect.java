package omnisphere.microsservices.User.application.aspect;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.application.anottations.CachingValue;
import omnisphere.microsservices.User.core.services.interfaces.cache.ICacheService;
import omnisphere.microsservices.User.infrastructure.cache.redis.settings.CacheSettings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
public class CacheValueAspect {
    private final ICacheService cacheService;

    @AfterReturning(pointcut = "@annotation(cachingValue)", returning = "retorno")
    public void logReturnData(ProceedingJoinPoint joinPoint, CachingValue cachingValue, Object retorno) throws Throwable {
        cacheService.put(cachingValue.key(), retorno);
    }
}
