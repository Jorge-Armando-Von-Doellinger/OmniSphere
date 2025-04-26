package omnisphere.microsservices.KeyAccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication

public class KeyAccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeyAccessApplication.class, args);
	}

}
