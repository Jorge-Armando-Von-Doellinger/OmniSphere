package omnisphere.microsservices.User.core.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("tb_user")  // Mapeia a tabela 'tb_user' do banco de dados
public class User {

    public User(String username, String email, String password) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    @Id
    @Setter(AccessLevel.NONE)
    @Column("user_id")
    private UUID id;

    @NotNull
    @NotBlank(message = "Username is required")
    @Column("username")
    private String username;

    @NotBlank(message = "Email is required!")
    @Email
    private String email;

    @NotBlank(message = "Password is required!")
    private String password;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;




    // Método para atualizar os dados do usuário
    public void update(String username, String email, String password) {
        if (username != null && !username.isBlank())
            this.username = username;
        if (email != null && !email.isBlank())
            this.email = email;
        if (password != null && !password.isBlank())
            this.password = password;
    }
}