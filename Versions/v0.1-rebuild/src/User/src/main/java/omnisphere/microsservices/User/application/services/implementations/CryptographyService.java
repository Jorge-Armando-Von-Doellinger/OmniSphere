package omnisphere.microsservices.User.application.services.implementations;

import omnisphere.microsservices.User.core.services.interfaces.cryptography.ICryptographyService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CryptographyService implements ICryptographyService {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    public String encrypt(String stringValue) {
        return encoder.encode(stringValue);
    }

    @Override
    public boolean verify(String value1, String value2) {
        return encoder.matches(value1, value2);
    }
}
