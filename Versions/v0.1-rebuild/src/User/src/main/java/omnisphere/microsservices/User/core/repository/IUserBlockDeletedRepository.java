package omnisphere.microsservices.User.core.repository;

import omnisphere.microsservices.User.core.entity.remove_representation.UserBlockRemoved;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IUserBlockDeletedRepository extends R2dbcRepository<UserBlockRemoved, String> {
    @Query("""
        SELECT *
        FROM tb_user_block_removed uur
        WHERE EXISTS (
            SELECT 1
            FROM tb_users_deleted ud
            WHERE ud.user_id = :userId
            """)
    Flux<UserBlockRemoved> findByUserId(String userId);
}
