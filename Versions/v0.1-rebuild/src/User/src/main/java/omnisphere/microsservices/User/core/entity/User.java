package omnisphere.microsservices.User.core.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Table("tb_user")  // Mapeia a tabela 'tb_user' do banco de dados
public class User extends UserFields {

    public User(String username, String email, String password) {
        super(username, email, password);
    }

    @Id
    @Setter(AccessLevel.NONE)
    @Column("id")
    private UUID id;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;

    // Método para atualizar os dados do usuário
    public void update(String username, String email, String password) {
        if (username != null && !username.isBlank())
            setUsername(username);
        if (email != null && !email.isBlank())
            setEmail(email);
        if (password != null && !password.isBlank())
            setPassword(password);
    }
}