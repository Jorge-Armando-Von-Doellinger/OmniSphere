package omnisphere.microsservices.KeyAccess.services.interfaces;

import omnisphere.microsservices.KeyAccess.entity.KeyAccess;
import omnisphere.microsservices.KeyAccess.model.KeyAccessModel;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface IKeyAccessService {
    Mono<KeyAccess> generate(KeyAccessModel model);
    Mono<KeyAccess> extend(KeyAccess keyAccess, Date expireOn);
    Mono<KeyAccess> get(String key, String userId);
}
