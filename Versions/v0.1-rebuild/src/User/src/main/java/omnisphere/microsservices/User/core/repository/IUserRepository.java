package omnisphere.microsservices.User.core.repository;

import omnisphere.microsservices.User.core.entity.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IUserRepository extends ReactiveCrudRepository<User, String> {
    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    Mono<User> findByEmailAndPassword(String email, String password);
    Mono<User> findByEmail(String email);

    @Query("""
    SELECT u.* 
    FROM users u 
    LEFT JOIN tb_user_block b ON u.id = b.user_id 
    WHERE b.id IS NULL
    """)
    Flux<User> findAllNeverBlocked();





}
