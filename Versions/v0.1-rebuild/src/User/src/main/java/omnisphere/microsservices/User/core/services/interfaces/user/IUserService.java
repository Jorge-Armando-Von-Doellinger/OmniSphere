package omnisphere.microsservices.User.core.services.interfaces.user;

import omnisphere.microsservices.User.core.entity.User;
import reactor.core.publisher.Mono;

public interface IUserService {

    // FAZER UM SERVICE SO PARA USER, ASSIM SEMPRE GARANTINDO QUE E A MESMA PESSOA
    // AUDIT SERVICE SEMPRE USANDO O ID DO USUARIO

    // FAZER UM SERVICE PARA O ADMIN, CUSTOMIZANDO O AUDIT (MELHOR GERENCIAMENTO)
    Mono<User> create(User user);
    /// Sends a partial user, containing username, email and/or password
    Mono<User> update(String userId, User partialUser);
    Mono<User> delete(String userId);
    Mono<User> findById(String userId);
    Mono<User> validate(String email, String password);
}
