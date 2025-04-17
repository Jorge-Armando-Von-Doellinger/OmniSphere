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
    public async Task StartAsync(LiveEntity live)
    {
        await _repository.Add(live);
    }

    public Task StopAsync(LiveEntity live)
    {
        throw new NotImplementedException();
    }
}