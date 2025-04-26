package omnisphere.microsservices.User.core.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("tb_user")  // Mapeia a tabela 'tb_user' do banco de dados
public class User extends BaseUser {

    public User(String username, String email, String password) {
        super.email = email;
        this.password = password;
        this.username = username;
    }

    @Id
    @Setter(AccessLevel.NONE)
    @Column("user_id")
    private String id;  // A chave primária (ID), provavelmente será gerado no banco ou manualmente com UUID.

    @Setter(AccessLevel.NONE)
    private Date deletedAt = null;

    public void setDeleted() {
        this.deletedAt = new Date();
    }

    public boolean isDeleted() {
        return deletedAt != null;
    }

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