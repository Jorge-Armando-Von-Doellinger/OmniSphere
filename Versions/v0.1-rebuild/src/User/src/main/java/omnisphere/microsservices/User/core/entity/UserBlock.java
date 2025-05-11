package omnisphere.microsservices.User.core.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("tb_user_block")
public class UserBlock extends BlockFields {
    @Id
    @Column("id")
    private Long blockId;

    @Column("user_id")
    private UUID userId;
}
