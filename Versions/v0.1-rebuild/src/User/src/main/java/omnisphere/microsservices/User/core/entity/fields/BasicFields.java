package omnisphere.microsservices.User.core.entity.fields;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

public abstract class BasicFields {
    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
