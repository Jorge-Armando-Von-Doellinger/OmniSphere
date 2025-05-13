package omnisphere.microsservices.User.core.entity.remove_representation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import omnisphere.microsservices.User.core.entity.fields.UserFields;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table("tb_old_user_removed")
public class OldUserRemoved extends UserFields {

    @Id
    @Column("id")
    private Long deleteId;

    @Column("old_id")
    private Long updateId;

    @Column("remove_id")
    private UUID userDeleteId;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;

    @Column("deleted_at")
    private LocalDateTime deletedAt;

    public OldUserRemoved(String username, String email, String password) {
        super(username, email, password);
    }
}
