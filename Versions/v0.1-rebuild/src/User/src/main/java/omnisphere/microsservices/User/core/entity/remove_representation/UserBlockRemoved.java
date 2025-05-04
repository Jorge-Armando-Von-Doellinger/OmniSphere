package omnisphere.microsservices.User.core.entity.remove_representation;

import lombok.Data;
import omnisphere.microsservices.User.core.entity.base.BaseBlock;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("tb_user_block_removed")
public class UserBlockRemoved extends BaseBlock {
    @Id
    @Column("delete_id")
    private Long id;

    @Column("block_id")
    private Long blockId;

    @Column("user_delete_id")
    private UUID userId;

    @CreatedDate
    @Column("deleted_at")
    private LocalDateTime deletedAt;
}
