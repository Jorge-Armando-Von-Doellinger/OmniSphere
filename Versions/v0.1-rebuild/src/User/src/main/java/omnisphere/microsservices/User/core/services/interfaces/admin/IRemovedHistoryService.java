package omnisphere.microsservices.User.core.services.interfaces.admin;

import omnisphere.microsservices.User.core.entity.history.UserHistory;
import omnisphere.microsservices.User.core.entity.history.UserRemovedHistory;
import reactor.core.publisher.Mono;

public interface IRemovedHistoryService {
    Mono<UserRemovedHistory> make(String userId);
}
