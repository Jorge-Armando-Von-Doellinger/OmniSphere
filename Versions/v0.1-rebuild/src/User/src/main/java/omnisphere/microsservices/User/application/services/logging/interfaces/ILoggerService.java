package omnisphere.microsservices.User.application.services.logging.interfaces;

import omnisphere.microsservices.User.core.entity.Log;
import reactor.core.publisher.Mono;

public interface ILoggerService {
    Mono<Log> log(Log logging);
}
