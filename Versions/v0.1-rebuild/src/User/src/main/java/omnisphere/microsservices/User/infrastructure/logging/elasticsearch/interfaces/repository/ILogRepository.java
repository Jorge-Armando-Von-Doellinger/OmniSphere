package omnisphere.microsservices.User.infrastructure.logging.elasticsearch.interfaces.repository;

import omnisphere.microsservices.User.core.entity.Log;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILogRepository extends ReactiveElasticsearchRepository<Log, Long> {
}
