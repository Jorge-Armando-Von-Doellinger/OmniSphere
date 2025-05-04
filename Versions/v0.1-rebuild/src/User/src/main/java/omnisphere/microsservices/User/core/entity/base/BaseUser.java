package omnisphere.microsservices.User.core.entity.base;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Data
public abstract class BaseUser {
    @NotNull
    @NotBlank(message = "Username is required")
    @Column("username")
    protected String username;

    @NotBlank(message = "Email is required!")
    @Email
    protected String email;

    @NotBlank(message = "Password is required!")
    protected String password;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;
}
