package omnisphere.microsservices.User.core.repository;

import omnisphere.microsservices.User.core.entity.UserBlock;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface IUserBlockRepository extends R2dbcRepository<UserBlock, String> {
    //@Query("SELECT * FROM tb_user_block WHERE user_id = $1")
    Flux<UserBlock> findByUserId(String userId);

    // Blocos que foram desbloqueados (unblockReason preenchido)
    @Query(value = "SELECT * FROM tb_user_block WHERE unblock_reason IS NOT NULL AND unblock_reason <> ''")
    Flux<UserBlock> findAllUnblocked();

    // Blocos que estão ativos (sem motivo de desbloqueio)
    @Query(value = "SELECT * FROM tb_user_block WHERE unblock_reason IS NULL OR unblock_reason = ''")
    Flux<UserBlock> findAllBlocked();


    @Query(value = """
    SELECT DISTINCT ON (user_id) * 
    FROM tb_user_block 
    WHERE blocked_at IS NOT NULL AND unblocked_at IS NULL
    ORDER BY user_id, blockedAt DESC
    """)
    Flux<UserBlock> findDistinctLatestActiveBlocks();

    @Query(value = """
    SELECT DISTINCT ON (user_id) * 
    FROM tb_user_block 
    WHERE unblocked_at IS NOT NULL 
    ORDER BY user_id, unblocked_at DESC
    """)
    Flux<UserBlock> findDistinctLatestUnblocks();

    @Query("""
            SELECT *
            FROM tb_user_block
            WHERE blocked_at IS NOT NULL
            AND unblocked_at IS NULL
            AND user_id = :userId
            ORDER BY blocked_at DESC
            LIMIT 1
    """)
    Mono<UserBlock> findLatestActiveBlocks(@Param("userId") UUID userId);

    @Query("""
        SELECT *
        FROM tb_user_block
        WHERE blocked_at IS NOT NULL 
        AND unblocked_at IS NULL 
        AND user_id = :userId
        ORDER BY blocked_at DESC
        LIMIT 1
    """)
    Mono<UserBlock> findLatestUnblocks(@Param("userId") UUID userId);

}
