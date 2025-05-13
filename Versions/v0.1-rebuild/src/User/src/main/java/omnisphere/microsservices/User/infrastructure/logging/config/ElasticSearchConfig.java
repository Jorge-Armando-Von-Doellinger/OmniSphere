package omnisphere.microsservices.User.infrastructure.logging.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

@Configuration
@EnableReactiveElasticsearchRepositories(basePackages = "omnisphere.microsservices.User.core.repository")
@EnableElasticsearchRepositories(basePackages = "omnisphere.microsservices.User.core.repository")
public class ElasticSearchConfig {
}
