package omnisphere.microsservices.User.core.repository.oldUser;

import omnisphere.microsservices.User.core.entity.remove_representation.OldUserRemoved;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IOldUserRemovedRepository extends R2dbcRepository<OldUserRemoved, String> {
    @Query("""
        SELECT *
        FROM tb_user_update_removed uur
        WHERE EXISTS (
            SELECT 1
            FROM tb_users_deleted ud
            WHERE ud.user_id = :userId
            """)
    Flux<OldUserRemoved> findByUserId(String userId);
}
