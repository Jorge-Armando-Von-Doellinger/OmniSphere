package omnisphere.microsservices.User.core.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;


@Table("tb_user_update")
public class UserUpdate extends User {
    @Setter
    @Getter
    @Id
    @Column("update_id")
    private Long updateId;
    @Setter
    @Getter
    @CreatedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;

    public UserUpdate(String username, String email, String password) {
        super(username, email, password);
    }
}
