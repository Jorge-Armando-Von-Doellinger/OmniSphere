package omnisphere.microsservices.User.core.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@Table("tb_users_deleted")
public class UserRemoved extends BaseUser
{
    @Id
    @Column("user_delete_id")
    private Long id;

    @Column("user_id")
    private Long userId;

    @Column("blocks_id")
    private List<String> blocksId;

}
