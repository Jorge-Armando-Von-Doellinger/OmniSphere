package omnisphere.microsservices.User.infrastructure.database.postgresql.initializer;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

@Component
public class SqlInitializer {

    private final DataSource dataSource;

    public SqlInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void initialize() throws IOException, SQLException {
        runScript("sql/create_tables_if_not_exists.sql");
        runScript("sql/user_delete_trigger.sql");
    }

    private void runScript(String path) throws IOException, SQLException {
        var resource = new ClassPathResource(path);
        String sql = new String(Files.readAllBytes(resource.getFile().toPath()));
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            for (String command : sql.split(";")) {
                String trimmed = command.trim();
                if (!trimmed.isEmpty()) {
                    stmt.execute(trimmed);
                }
            }
        }
    }
}

