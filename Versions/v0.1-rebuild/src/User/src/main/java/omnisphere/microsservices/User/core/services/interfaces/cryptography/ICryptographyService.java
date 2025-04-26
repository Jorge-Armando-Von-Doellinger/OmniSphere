package omnisphere.microsservices.User.core.services.interfaces.cryptography;

import reactor.core.publisher.Mono;

public interface ICryptographyService {
    String encrypt(String stringValue);
    boolean verify(String value1, String value2);
}
