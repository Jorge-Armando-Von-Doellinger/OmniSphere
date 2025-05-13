package omnisphere.microsservices.User.core.entity.remove_representation;

import lombok.*;
import omnisphere.microsservices.User.core.entity.fields.UserFields;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

// Ajustar posteriormente
// Herdar User

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)

@Table("tb_users_deleted")
public class UserRemoved extends UserFields
{
    @Id
    @Column("id")
    private Long deleteId;
    @Column("user_id")
    private UUID userId;

    @Column("created_at")
    private LocalDateTime createdAt;
    @Column("updated_at")
    private LocalDateTime updatedAt;

    @CreatedDate
    @Column("deleted_at")
    private LocalDateTime deletedAt;

    public UserRemoved(String username, String email, String password) {
        super(username, email, password);
    }
}
