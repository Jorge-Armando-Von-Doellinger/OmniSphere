using OmniSphere.LiveStreamService.Core.Entity;

namespace OmniSphere.LiveStreamService.Core.Interfaces.Repository;

public interface ILiveRepository
{
    Task<LiveEntity> GetLatestByUsername(string username);
    Task<LiveEntity> GetById(string liveId);
    Task Add(LiveEntity live);
    Task Remove(string liveId);
}