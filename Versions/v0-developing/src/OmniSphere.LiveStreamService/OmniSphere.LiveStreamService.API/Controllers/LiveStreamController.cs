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
        // Key access will be validated on the middleware: KeyAccessMiddleware
        var registeredLive = await _liveStreamUseCase.RegisterAsync(live);
        return Ok(registeredLive);
    }
    
    
    /// <summary>
    /// This "name" is the key access. Placed this name for the obs studio can send a key access!
    /// </summary>
    /// <param name="name"></param>
    /// <returns> CODE 200 </returns>
    [HttpPost("start")]
    public async Task<IActionResult> StartLive([FromForm] string name) // nginx
    {
        // don't have a validation of middleware (in this moment)
        var live = await _liveStreamUseCase.GetLatestLiveByKeyAccessAsync(name);
        await _liveStreamUseCase.StartAsync(live);
        return Ok(live);
    }

    [HttpPost("stop")]
    public async Task<IActionResult> StopLive([FromForm] string name) // nginx
    {
        var live = await _liveStreamUseCase.GetLatestLiveByKeyAccessAsync(name);
        await _liveStreamUseCase.StopAsync(live);
        return Ok(live);
    }
}