package omnisphere.microsservices.KeyAccess.repository;

import omnisphere.microsservices.KeyAccess.entity.KeyAccess;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface IKeyAccessRepository extends ReactiveMongoRepository<KeyAccess, String> {
    Mono<KeyAccess> findByKey(String key);
    Mono<List<KeyAccess>> findByUserId(String userId);
}
