package omnisphere.microsservices.KeyAccess.application.services.interfaces;

import omnisphere.microsservices.KeyAccess.core.entity.KeyAccess;
import omnisphere.microsservices.KeyAccess.application.model.KeyAccessModel;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IKeyAccessService {
    Mono<KeyAccess> generate(KeyAccessModel model);
    Mono<KeyAccess> extend(KeyAccess keyAccess, Date expireOn);
    Mono<KeyAccess> get(String key, String userId);
}
