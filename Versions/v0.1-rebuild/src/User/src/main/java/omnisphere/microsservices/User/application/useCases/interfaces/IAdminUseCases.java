package omnisphere.microsservices.User.application.useCases.interfaces;

import omnisphere.microsservices.User.core.entity.User;
import reactor.core.publisher.Flux;

public interface IAdminUseCases {
    Flux<User> getAll();
    Flux<User> get(String userId);
}
