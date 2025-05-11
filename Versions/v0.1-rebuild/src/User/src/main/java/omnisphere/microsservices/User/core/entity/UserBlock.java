package omnisphere.microsservices.User.core.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("tb_user_block")
public class UserBlock  {
    @Id
    @Column("block_id")
    private Long block_id;

    @Column("block_reason")
    protected String blockReason;

    @Column("unblock_reason")
    protected String unblockReason;

    @CreatedDate
    @Column("blocked_at")
    protected LocalDateTime blockedAt;
    protected LocalDateTime unblockedAt = null;

    @Column("user_id")
    private UUID userId;


}
