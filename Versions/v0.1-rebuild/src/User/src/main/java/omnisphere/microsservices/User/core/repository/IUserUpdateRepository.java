package omnisphere.microsservices.User.core.repository;

import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.UserUpdate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IUserUpdateRepository extends ReactiveCrudRepository<UserUpdate, String> {
}
