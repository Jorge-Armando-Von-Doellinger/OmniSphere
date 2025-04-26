package omnisphere.microsservices.User.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table("tb_user_block")
public class UserBlock extends BaseBlock{
    @Id
    @Column("user_block_id")
    private Long id;

    @Column("user_id")
    private String userId;


}
