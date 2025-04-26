package omnisphere.microsservices.KeyAccess.core.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;
import java.util.Date;

@Data
@Document(collation = "key_access_collection")
public class KeyAccess {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    @NonNull
    private final String key;
    @NonNull
    private final String userId;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private Date createdAt;
    @NonNull
    @Setter(AccessLevel.NONE)
    private Date expireOn;

    public boolean isExpired() {
        var now =  new Date();
        return now.toInstant().isAfter(expireOn.toInstant());
    }

    public void extend(Date expireOn) {
        this.expireOn = expireOn;
    }
}
