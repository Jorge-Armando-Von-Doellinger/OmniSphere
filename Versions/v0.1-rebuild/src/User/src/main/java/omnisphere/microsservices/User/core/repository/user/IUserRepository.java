package omnisphere.microsservices.User.core.repository.user;

import omnisphere.microsservices.User.core.entity.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IUserRepository extends R2dbcRepository<User, String> {
    Mono<User> findByEmail(String email);
    Mono<User> findByUsername(String username); // Alterar

    @Query("""
    SELECT u.*
    FROM tb_user u
    RIGHT JOIN tb_user_block b ON u.id = b.user_id
    WHERE unblock_reason IS NULL OR unblock_reason = '';
    """)
    Flux<User> findAllBlocked();



}
