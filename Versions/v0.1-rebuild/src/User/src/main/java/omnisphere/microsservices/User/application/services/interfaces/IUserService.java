package omnisphere.microsservices.User.application.services.interfaces;

import omnisphere.microsservices.User.application.dto.UserDTO;
import omnisphere.microsservices.User.core.entity.User;
import reactor.core.publisher.Mono;

public interface IUserService {

    // FAZER UM SERVICE SO PARA USER, ASSIM SEMPRE GARANTINDO QUE E A MESMA PESSOA
    // AUDIT SERVICE SEMPRE USANDO O ID DO USUARIO

    // FAZER UM SERVICE PARA O ADMIN, CUSTOMIZANDO O AUDIT (MELHOR GERENCIAMENTO)
    Mono<User> create(UserDTO model);
    Mono<User> update(String userId, UserDTO model);
    Mono<User> delete(String userId);
    Mono<User> findById(String userId);
    Mono<User> findByCredentials(UserDTO model);
}
