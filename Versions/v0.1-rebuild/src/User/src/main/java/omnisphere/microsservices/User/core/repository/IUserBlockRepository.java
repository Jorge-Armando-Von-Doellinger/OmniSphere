package omnisphere.microsservices.User.core.repository;

import omnisphere.microsservices.User.core.entity.UserBlock;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IUserBlockRepository extends ReactiveCrudRepository<UserBlock, String> {
    // Blocos que foram desbloqueados (unblockReason preenchido)
    @Query(value = "SELECT * FROM tb_user_block WHERE unblock_reason IS NOT NULL AND unblock_reason <> ''")
    Flux<UserBlock> findAllUnblocked();

    // Blocos que estão ativos (sem motivo de desbloqueio)
    @Query(value = "SELECT * FROM tb_user_block WHERE unblock_reason IS NULL OR unblock_reason = ''")
    Flux<UserBlock> findAllBlocked();


    @Query(value = """
    SELECT DISTINCT ON (user_id) * 
    FROM tb_user_block 
    WHERE blockedAt IS NOT NULL AND unblockedAt IS NULL
    ORDER BY user_id, blockedAt DESC
    """)
    Flux<UserBlock> findDistinctLatestActiveBlocks();

    @Query(value = """
    SELECT DISTINCT ON (user_id) * 
    FROM tb_user_block 
    WHERE unblockedAt IS NOT NULL 
    ORDER BY user_id, unblockedAt DESC
    """)
    Flux<UserBlock> findDistinctLatestUnblocks();
}
