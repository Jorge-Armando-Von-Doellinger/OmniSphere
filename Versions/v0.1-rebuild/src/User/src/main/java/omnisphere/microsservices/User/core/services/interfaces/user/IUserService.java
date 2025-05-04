package omnisphere.microsservices.User.core.services.interfaces.user;

import omnisphere.microsservices.User.core.entity.User;
import reactor.core.publisher.Mono;

public interface IUserService {

    // FAZER UM SERVICE SO PARA USER, ASSIM SEMPRE GARANTINDO QUE E A MESMA PESSOA
    // AUDIT SERVICE SEMPRE USANDO O ID DO USUARIO

    // FAZER UM SERVICE PARA O ADMIN, CUSTOMIZANDO O AUDIT (MELHOR GERENCIAMENTO)
    Mono<User> create(User model);
    Mono<User> update(User user);
    Mono<User> delete(String userId);
    Mono<User> findById(String userId);
}
