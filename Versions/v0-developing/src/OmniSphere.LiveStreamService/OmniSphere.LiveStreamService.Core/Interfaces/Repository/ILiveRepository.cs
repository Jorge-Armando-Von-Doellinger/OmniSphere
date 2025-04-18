using OmniSphere.LiveStreamService.Core.Entity;

namespace OmniSphere.LiveStreamService.Core.Interfaces.Repository;

public interface ILiveRepository
{
    Task<LiveEntity> GetLatestByKeyAccessAsync(string keyAccess);
    Task<LiveEntity> GetByIdAsync(string liveId);
    Task AddAsync(LiveEntity live);
    Task UpdateAsync(LiveEntity live);
    Task RemoveAsync(string liveId);
}