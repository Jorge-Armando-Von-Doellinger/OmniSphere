package omnisphere.microsservices.User.core.repository.log;

import com.sun.jna.WString;
import omnisphere.microsservices.User.core.entity.Log;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILogRepository extends ReactiveElasticsearchRepository<Log, String> {
}
