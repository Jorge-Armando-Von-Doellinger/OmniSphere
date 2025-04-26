package omnisphere.microsservices.User.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table("tb_user_block_removed")
public class UserBlockRemoved extends BaseBlock {
    @Id
    @Column("block_remove_id")
    private Long id;

    @Column("block_id")
    private Long blockId;

    @Column("user_id")
    private String userId;

    @CreatedDate
    @Column("deleted_at")
    private LocalDateTime deletedAt;
}
