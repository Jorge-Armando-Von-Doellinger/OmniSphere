package omnisphere.microsservices.User.core.entity.remove_representation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("tb_user_update_removed")
public class UserUpdateRemoved extends UserRemoved {
    @Getter
    @Setter
    @Id
    @Column("delete_id")
    private Long deleteId;
    @Column("update_id")
    @Getter
    @Setter
    private Long updateId;
    @Column("user_id")
    private UUID userId;
    @Getter
    @Setter
    @CreatedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;
    @Getter
    @Setter
    @CreatedDate
    @Column("deleted_at")
    private LocalDateTime deletedAt;

    public UserUpdateRemoved(String username, String email, String password) {
        super(username, email, password);
    }
}
