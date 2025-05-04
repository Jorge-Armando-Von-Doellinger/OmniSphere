package omnisphere.microsservices.User.core.entity;

import lombok.Data;
import omnisphere.microsservices.User.core.entity.base.BaseUser;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("tb_user_update")
public class UserUpdate extends BaseUser {
    @Id
    @Column("update_id")
    private Long id;
    @Column("user_id")
    private UUID userId;
    @CreatedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;
}
