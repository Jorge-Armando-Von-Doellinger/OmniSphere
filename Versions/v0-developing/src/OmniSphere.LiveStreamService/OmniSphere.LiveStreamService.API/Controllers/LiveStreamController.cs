using Microsoft.AspNetCore.Mvc;
using OmniSphere.LiveStreamService.Application.UseCases.Interfaces;
using OmniSphere.LiveStreamService.Core.Entity;

namespace OmniSphere.LiveStreamService.API.Controllers;

[ApiController]
[Route("/api/live")]
public class LiveStreamController : ControllerBase
{
    private readonly ILiveStreamUseCase _liveStreamUseCase;
    public LiveStreamController(ILiveStreamUseCase liveStreamUseCase)
    {
        _liveStreamUseCase = liveStreamUseCase;
    }
    
    [HttpPost("register")]
    public async Task<IActionResult> RegisterLive([FromBody] LiveEntity live)
    {
        var registeredLive = await _liveStreamUseCase.RegisterAsync(live);
        return Ok();
    }

    [HttpPost("start")]
    public async Task<IActionResult> StartLive([FromForm] string name) // nginx
    {
        var live = await _liveStreamUseCase.GetLatestLiveByKeyAccessAsync(name);
        await _liveStreamUseCase.StartAsync(live);
        return Ok();
    }

    [HttpPost("stop")]
    public async Task<IActionResult> StopLive([FromForm] string name) // nginx
    {
        var live = await _liveStreamUseCase.GetLatestLiveByKeyAccessAsync(name);
        await _liveStreamUseCase.StopAsync(live);
        return Ok();
    }
}