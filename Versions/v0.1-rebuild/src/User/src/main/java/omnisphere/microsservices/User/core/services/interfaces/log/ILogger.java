package omnisphere.microsservices.User.core.services.interfaces.log;

import omnisphere.microsservices.User.core.entity.Log;
import omnisphere.microsservices.User.core.enums.ErrorLevel;
import reactor.core.publisher.Mono;


public interface ILogger {
    Mono<Log> log(Log log);

    Mono<Log> log(Object data, ErrorLevel level, Throwable ex);
}
