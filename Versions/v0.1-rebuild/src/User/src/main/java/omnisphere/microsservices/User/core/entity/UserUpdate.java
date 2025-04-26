package omnisphere.microsservices.User.core.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table("update_user_table")
public class UserUpdate extends BaseUser {
    @Id
    @Column("user_update_id")
    private Long id;
    @Column("user_id")
    private Long userId;
    @CreatedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;
}
