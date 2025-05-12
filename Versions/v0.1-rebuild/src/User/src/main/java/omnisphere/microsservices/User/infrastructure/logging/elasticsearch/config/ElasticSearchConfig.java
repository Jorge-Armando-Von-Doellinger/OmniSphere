package omnisphere.microsservices.User.infrastructure.logging.elasticsearch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories("omnisphere.microsservices.User.infrastructure.logging.elasticsearch.interfaces.repository")
@EnableReactiveElasticsearchRepositories("omnisphere.microsservices.User.infrastructure.logging.elasticsearch.interfaces.repository")

public class ElasticSearchConfig {
    public String uri;
}
