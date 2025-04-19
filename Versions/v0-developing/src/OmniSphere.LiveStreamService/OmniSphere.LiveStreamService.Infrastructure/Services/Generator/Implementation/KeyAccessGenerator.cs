using OmniSphere.LiveStreamService.Core.Entity;
using OmniSphere.LiveStreamService.Core.Interfaces.Services.Generator;

namespace OmniSphere.LiveStreamService.Infrastructure.Services.Generator.Implementation;

public class KeyAccessGenerator : IKeyAccessGenerator
{
    public async Task<KeyAccessEntity> GenerateKeyAccess(int durationInMinutes, string userId)
    {
        var keyAccess = new KeyAccessEntity()
        {
            KeyAccess = GenerateEncryptedRandomKeyAccess(),
            UserId = userId
        };
        keyAccess.SetExpireIn(durationInMinutes);
        return keyAccess;
    }

    private string GenerateEncryptedRandomKeyAccess()
    {
        var guid = Guid.NewGuid().ToString();
        var hashedGuid = BCrypt.Net.BCrypt.HashPassword(guid);
        return hashedGuid ;
    }
}