package omnisphere.microsservices.User.core.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Data
public abstract class BaseBlock {
    @Column("block_reason")
    protected String blockReason;

    @Column("unblock_reason")
    protected String unblockReason;

    @CreatedDate
    @Column("blocked_at")
    protected LocalDateTime blockedAt;
    protected LocalDateTime unblockedAt = null;
}
