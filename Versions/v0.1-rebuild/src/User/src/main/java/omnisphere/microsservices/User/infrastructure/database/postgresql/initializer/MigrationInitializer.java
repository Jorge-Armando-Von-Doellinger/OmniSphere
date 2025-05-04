package omnisphere.microsservices.User.infrastructure.database.postgresql.initializer;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.xml.stream.FactoryConfigurationError;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Configuration
public class MigrationInitializer {

    private final DatabaseClient client;

    public MigrationInitializer(DatabaseClient client) {
        this.client = client;
    }

    @Bean
    public ApplicationRunner runMigrations(DatabaseClient client) {
        return args -> {
            Map<String, String> schemaToFile = Map.of(
                    //"V1__create_tables", "db/sql/migrations/V1__create_tables.sql",
                    "V1__audit_triggers", "db/sql/migrations/user_delete_trigger.sql"
            );

            Flux.fromIterable(schemaToFile.entrySet())


                    .concatMap(entry -> applyMigration(client, entry.getKey(), entry.getValue()))
                    .then()
                    .block();
        };
    }

    private Mono<Void> applyMigration(DatabaseClient client, String schema, String path) {
        try {
            ClassPathResource resource = new ClassPathResource(path);
            String sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            return client.sql(sql)
                    .then();
        } catch (Exception e) {
            return Mono.error(e);
        }
    }
}

