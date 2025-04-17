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
    
    [HttpPost]
    public async Task<IActionResult> StartLive([FromBody] LiveEntity live)
    {
        await _liveStreamUseCase.StartAsync(live);
        return Ok();
    }
    [HttpPost("/stop")]
    public async Task<IActionResult> StopLive([FromBody] LiveEntity live)
    {
        await _liveStreamUseCase.StopAsync(live);
        return Ok();
    }
}