package omnisphere.microsservices.User.core.entity.remove_representation;

import lombok.Data;
import omnisphere.microsservices.User.core.entity.UserBlock;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/// melhorar - deivdo a duplicação de codigo!

@Data
@Table("tb_user_block_removed")
public class UserBlockRemoved extends UserBlock {
    @Id
    @Column("delete_id")
    private Long id;

    @CreatedDate
    @Column("deleted_at")
    private LocalDateTime deletedAt;
}
