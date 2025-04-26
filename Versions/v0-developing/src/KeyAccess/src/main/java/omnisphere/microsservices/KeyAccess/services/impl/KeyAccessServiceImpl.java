package omnisphere.microsservices.KeyAccess.services.impl;

import omnisphere.microsservices.KeyAccess.entity.KeyAccess;
import omnisphere.microsservices.KeyAccess.exceptions.FailedSaveEntityException;
import omnisphere.microsservices.KeyAccess.exceptions.InvalidKeyAccessException;
import omnisphere.microsservices.KeyAccess.exceptions.KeyAccessNotFoundException;
import omnisphere.microsservices.KeyAccess.model.KeyAccessModel;
import omnisphere.microsservices.KeyAccess.repository.IKeyAccessRepository;
import omnisphere.microsservices.KeyAccess.services.interfaces.IKeyAccessService;
import omnisphere.microsservices.KeyAccess.services.interfaces.IKeyGenerator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

@Service
public class KeyAccessServiceImpl implements IKeyAccessService {
    private final IKeyGenerator generator;
    private final IKeyAccessRepository repository;

    public KeyAccessServiceImpl(IKeyGenerator generator,
                                IKeyAccessRepository repository) {
        this.generator = generator;
        this.repository = repository;
    }
    @Override
    public Mono<KeyAccess> generate(KeyAccessModel model) {
        var keyAccess = generator.GenerateRandomTokenAsync()
                .onErrorResume(FailedSaveEntityException.class, e -> Mono.empty()) // case error
                .flatMap(key -> {
                    var keyaccess = new KeyAccess(key, model.userId(), model.expireOn()); // create a keyaccess with a random key
                    return repository.save(keyaccess); // Save the entity
                });
        return keyAccess;
    }

    @Override
    public Mono<KeyAccess> extend(KeyAccess keyAccess, Date expireOn) {
        var current = repository
                .findByKey(keyAccess.getKey())
                .filter(x -> x.getUserId().equals(keyAccess.getUserId())) // Validate if userId its equals
                .flatMap(ka -> {
            ka.extend(expireOn);
            return repository.save(ka);
        });
        return current;
    }

    @Override
    public Mono<KeyAccess> get(String key, String userId) {
        return repository.findByKey(key)
                .filter(x -> !x.isExpired() && x.getUserId().equals(userId))
                .switchIfEmpty(Mono.error(KeyAccessNotFoundException::new));
    }
}
