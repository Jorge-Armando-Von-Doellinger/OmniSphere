package omnisphere.microsservices.User.core.repository;

import omnisphere.microsservices.User.core.entity.UserRemoved;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDeletedRepository extends ReactiveCrudRepository<UserRemoved, String> {
}
