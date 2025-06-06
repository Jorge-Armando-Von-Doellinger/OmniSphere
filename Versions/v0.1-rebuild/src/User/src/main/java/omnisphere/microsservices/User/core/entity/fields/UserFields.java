package omnisphere.microsservices.User.core.entity.fields;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

@Data
public abstract class UserFields extends BasicFields {

    public UserFields(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public UserFields() {}

    @NotNull
    @NotBlank(message = "Username is required")
    @Column("username")
    private String username;

    @NotBlank(message = "Email is required!")
    @Email
    private String email;

    @NotBlank(message = "Password is required!")
    private String password;
}
