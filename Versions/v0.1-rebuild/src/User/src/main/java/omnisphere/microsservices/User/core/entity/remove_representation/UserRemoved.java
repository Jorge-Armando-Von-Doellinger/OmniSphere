package omnisphere.microsservices.User.core.entity.remove_representation;

import lombok.Data;
import omnisphere.microsservices.User.core.entity.base.BaseUser;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Table("tb_users_deleted")
public class UserRemoved extends BaseUser
{
    @Id
    @Column("delete_id")
    private Long id;

    @Column("user_id")
    private UUID userId;

    @Column("blocks_id")
    private List<String> blocksId;

    @CreatedDate
    @Column("deleted_at")
    private LocalDateTime deletedAt;

}
