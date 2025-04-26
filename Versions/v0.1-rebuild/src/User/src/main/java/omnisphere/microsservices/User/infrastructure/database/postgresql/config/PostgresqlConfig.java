package omnisphere.microsservices.User.infrastructure.database.postgresql.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "omnisphere.microsservices.User.core.repository")
@EnableAutoConfiguration
public class PostgresqlConfig {
}
