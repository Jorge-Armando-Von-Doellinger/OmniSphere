using OmniSphere.LiveStreamService.Core.Entity;

namespace OmniSphere.LiveStreamService.Application.UseCases.Interfaces;

public interface ILiveStreamUseCase
{
    Task StartAsync(LiveEntity live);
    Task StopAsync(LiveEntity live);
}