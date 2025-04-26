package omnisphere.microsservices.KeyAccess.model;

import java.util.Date;

public record KeyAccessModel(Date expireOn, String userId)
{
}
