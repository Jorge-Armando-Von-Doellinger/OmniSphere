using OmniSphere.LiveStreamService.Application.UseCases.Interfaces;
using OmniSphere.LiveStreamService.Core.Entity;
using OmniSphere.LiveStreamService.Core.Interfaces.Repository;

namespace OmniSphere.LiveStreamService.Application.UseCases.Implementations;

public class LiveStreamUseCase : ILiveStreamUseCase
{
    private readonly ILiveRepository _repository;

    public LiveStreamUseCase(ILiveRepository repository)
    {
        _repository = repository;
    }

    public async Task<LiveEntity> GetLiveByIdAsync(string liveId)
    {
        return await _repository.GetByIdAsync(liveId);
    }

    public async Task<LiveEntity> GetLatestLiveByKeyAccessAsync(string keyAccess)
    {
        return await _repository.GetLatestByKeyAccessAsync(keyAccess);
    }

    public async Task<LiveEntity> RegisterAsync(LiveEntity live)
    {
        await _repository.AddAsync(live);
        return await _repository.GetByIdAsync(live.LiveId);
    }

    public async Task StartAsync(LiveEntity live)
    {
        await _repository.UpdateAsync(live);
    }

    public async Task StopAsync(LiveEntity live)
    {
        live.Stop();
        await _repository.UpdateAsync(live);
    }
}