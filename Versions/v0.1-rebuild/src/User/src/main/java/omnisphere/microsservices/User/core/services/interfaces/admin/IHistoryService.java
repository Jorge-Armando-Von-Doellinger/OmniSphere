package omnisphere.microsservices.User.core.services.interfaces.admin;

import omnisphere.microsservices.User.core.entity.history.IHistory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IHistoryService<THistory extends IHistory> {
    /// Heavy operation
    Mono<THistory> make(String userId);
    /// Caution, VERY HEAVY operation!
    Flux<THistory> make();



}

