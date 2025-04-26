package omnisphere.microsservices.KeyAccess.application.model;

import java.util.Date;

public record KeyAccessModel(Date expireOn, String userId)
{
}
