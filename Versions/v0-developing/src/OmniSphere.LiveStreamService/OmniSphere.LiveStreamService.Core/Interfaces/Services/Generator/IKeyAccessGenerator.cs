using OmniSphere.LiveStreamService.Core.Entity;

namespace OmniSphere.LiveStreamService.Core.Interfaces.Services.Generator;

public interface IKeyAccessGenerator
{
    Task<KeyAccessEntity> GenerateKeyAccess(int durationInMinutes, string userId);
}
