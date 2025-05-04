package omnisphere.microsservices.User.core.entity;

import lombok.Data;
import omnisphere.microsservices.User.core.entity.base.BaseBlock;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("tb_user_block")
public class UserBlock extends BaseBlock {
    @Id
    @Column("block_id")
    private Long id;

    @Column("user_id")
    private UUID userId;


}
