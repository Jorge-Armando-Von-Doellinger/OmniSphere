package omnisphere.microsservices.User.application.services.cryptography;

import omnisphere.microsservices.User.core.services.interfaces.cryptography.ICryptographyService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CryptographyService implements ICryptographyService {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    public String encrypt(String stringValue) {
        return encoder.encode(stringValue);
    }

    @Override
    public boolean verify(String value, String encoded) {
        return encoder.matches(value, encoded);
    }
}
