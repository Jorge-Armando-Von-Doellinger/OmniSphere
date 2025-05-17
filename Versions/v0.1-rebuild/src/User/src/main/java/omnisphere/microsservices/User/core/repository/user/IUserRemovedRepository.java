package omnisphere.microsservices.User.core.repository.user;

import omnisphere.microsservices.User.core.entity.remove_representation.UserRemoved;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IUserRemovedRepository extends R2dbcRepository<UserRemoved, String> {
    Flux<UserRemoved> findByEmail(String email);
    Flux<UserRemoved> findByUsername(String username);

    Mono<UserRemoved> findByUserId(String userId);


    @Query("""
    SELECT u.*
    FROM tb_user u
    RIGHT JOIN tb_user_block_removed b ON u.remove_id = b.id
    WHERE unblock_reason IS NULL OR unblock_reason = '';
    """)
    Flux<UserRemoved> findAllBlocked();
}
