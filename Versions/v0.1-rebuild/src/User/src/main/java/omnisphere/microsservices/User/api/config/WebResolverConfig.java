package omnisphere.microsservices.User.api.config;

import lombok.AllArgsConstructor;

import omnisphere.microsservices.User.api.resolvers.CurrentUserArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;


@Configuration
@AllArgsConstructor
public class WebResolverConfig implements WebFluxConfigurer {
    private final CurrentUserArgumentResolver resolver;

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        configurer.addCustomResolver(resolver);
    }

}
