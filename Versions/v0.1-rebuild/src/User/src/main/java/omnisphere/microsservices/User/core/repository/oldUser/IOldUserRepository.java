package omnisphere.microsservices.User.core.repository.oldUser;

import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.OldUser;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IOldUserRepository extends R2dbcRepository<OldUser, String> {
    //@Query("SELECT * FROM tb_user_update WHERE user_id = :userId")
    Flux<OldUser> findByUserId(String userId);

    Flux<User> findByEmail(String email);
}
