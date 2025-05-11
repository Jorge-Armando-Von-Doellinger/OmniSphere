package omnisphere.microsservices.User.core.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Table("tb_old_user")
public class OldUser extends UserFields {
    @Id
    @Column("id")
    private Long updateId;

    @Column("user_id")
    private UUID userId;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;

    public OldUser(String username, String email, String password) {
        super(username, email, password);
    }

    /*public UserUpdate(String username, String email, String password) {
        super(username, email, password);
    }*/
}
