package omnisphere.microsservices.User.core.entity.remove_representation;

import omnisphere.microsservices.User.core.entity.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

// Ajustar posteriormente
// Herdar User


@Table("tb_users_deleted")
public class UserRemoved extends User
{
    @Id
    @Column("delete_id")
    private Long deleteId;

    @CreatedDate
    @Column("deleted_at")
    private LocalDateTime deletedAt;

    public UserRemoved(String username, String email, String password) {
        super(username, email, password);
    }
}
