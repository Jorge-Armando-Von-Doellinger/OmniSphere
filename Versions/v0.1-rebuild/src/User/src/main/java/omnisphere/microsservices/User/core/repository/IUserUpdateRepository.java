package omnisphere.microsservices.User.core.repository;

import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.UserUpdate;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IUserUpdateRepository extends R2dbcRepository<UserUpdate, String> {
    //@Query("SELECT * FROM tb_user_update WHERE user_id = :userId")
    Flux<UserUpdate> findByUserId(String userId);

    Flux<User> findByEmail(String email);
}
