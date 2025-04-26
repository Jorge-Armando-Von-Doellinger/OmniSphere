package omnisphere.microsservices.User.application.services.logging.implementations;

import lombok.AllArgsConstructor;
import omnisphere.microsservices.User.core.entity.Log;
import omnisphere.microsservices.User.application.services.logging.interfaces.ILoggerService;
import omnisphere.microsservices.User.infrastructure.logging.elasticsearch.interfaces.repository.ILogRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class LoggingService implements ILoggerService {
    private final ILogRepository repository;
    @Override
    public Mono<Log> log(Log logging) {
        return repository.save(logging);
    }
}
