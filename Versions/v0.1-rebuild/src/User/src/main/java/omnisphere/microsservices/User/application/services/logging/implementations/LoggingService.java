package omnisphere.microsservices.User.application.services.logging.implementations;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.core.entity.Log;
import omnisphere.microsservices.User.core.enums.ErrorLevel;
import omnisphere.microsservices.User.core.repository.log.ILogRepository;
import omnisphere.microsservices.User.core.services.interfaces.log.ILogger;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class LoggingService implements ILogger {
    private final ILogRepository repository;

    @Override
    public Mono<Log> log(Log log) {
        return repository.save(log);
    }

    @Override
    public Mono<Log> log(Object data, ErrorLevel level, Throwable ex) {
        return this.log(new Log(data, level, ex));
    }
}
