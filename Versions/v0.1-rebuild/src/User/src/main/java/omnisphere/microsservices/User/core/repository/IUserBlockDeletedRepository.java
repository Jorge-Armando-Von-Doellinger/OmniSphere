package omnisphere.microsservices.User.core.repository;

import omnisphere.microsservices.User.core.entity.UserBlockRemoved;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserBlockDeletedRepository extends ReactiveCrudRepository<UserBlockRemoved, String> {
}
