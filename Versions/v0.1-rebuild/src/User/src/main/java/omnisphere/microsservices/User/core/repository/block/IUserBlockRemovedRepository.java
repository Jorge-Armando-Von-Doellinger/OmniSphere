package omnisphere.microsservices.User.core.repository.block;

import omnisphere.microsservices.User.core.entity.UserBlock;
import omnisphere.microsservices.User.core.entity.remove_representation.UserBlockRemoved;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IUserBlockRemovedRepository extends R2dbcRepository<UserBlockRemoved, String> {
    @Query("""
        SELECT *
        FROM tb_user_block_removed uur
        WHERE EXISTS (
            SELECT 1
            FROM tb_users_deleted ud
            WHERE ud.user_id = :userId
            """)
    Flux<UserBlockRemoved> findByUserId(String userId);

    @Query("""
            SELECT *
            FROM tb_user_block_removed
            WHERE blocked_at IS NOT NULL
            AND unblocked_at IS NULL
            AND user_id = :userId
            ORDER BY blocked_at DESC
            LIMIT 1
    """)
    Mono<UserBlock> findLatestActiveBlocks(String userId);

    @Query("""
        SELECT *
        FROM tb_user_block_removed
        WHERE blocked_at IS NOT NULL 
        AND unblocked_at IS NULL 
        AND user_id = :userId
        ORDER BY blocked_at DESC
        LIMIT 1
    """)
    Mono<UserBlock> findLatestUnblocks(String userId);
}
