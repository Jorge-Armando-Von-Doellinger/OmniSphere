package omnisphere.microsservices.KeyAccess.infrastructure.mongodb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableReactiveMongoRepositories("omnisphere.microsservices.KeyAccess.infrastructure.repository")
@EnableReactiveMongoAuditing()
public class MongoConfig {
}
