using OmniSphere.LiveStreamService.Core.Entity;

namespace OmniSphere.LiveStreamService.Core.Interfaces.Repository;

public interface IKeyAccessRepository
{
    Task AddAsync(KeyAccessEntity keyAccess);
    Task ExtendAsync(string keyAccess, int durationInMinutes);
    Task RemoveAsync(string keyAccess);
    Task<KeyAccessEntity> GetByKeyAccess(string keyAccess);
    Task<List<KeyAccessEntity>> GetAllByUserIdAsync(string userId);
}