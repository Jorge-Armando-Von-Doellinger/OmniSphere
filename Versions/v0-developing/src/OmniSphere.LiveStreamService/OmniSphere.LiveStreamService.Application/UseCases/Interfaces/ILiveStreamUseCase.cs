using OmniSphere.LiveStreamService.Core.Entity;

namespace OmniSphere.LiveStreamService.Application.UseCases.Interfaces;

public interface ILiveStreamUseCase
{
    Task<LiveEntity> GetLiveByIdAsync(string liveId);
    Task<LiveEntity> GetLatestLiveByKeyAccessAsync(string liveId);
    Task<LiveEntity> RegisterAsync(LiveEntity live);
    Task StartAsync(LiveEntity live);
    Task StopAsync(LiveEntity live);
}