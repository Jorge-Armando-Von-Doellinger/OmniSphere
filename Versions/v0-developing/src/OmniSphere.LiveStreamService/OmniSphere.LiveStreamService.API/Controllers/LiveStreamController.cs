using Microsoft.AspNetCore.Mvc;
using OmniSphere.LiveStreamService.Application.UseCases.Interfaces;
using OmniSphere.LiveStreamService.Core.Entity;

namespace OmniSphere.LiveStreamService.API.Controllers;

[ApiController]
[Route("/api/live")]
public class LiveStreamController : ControllerBase
{
    private readonly ILiveStreamUseCase _liveStreamUseCase;
    private static string guid = Guid.NewGuid().ToString().Replace("-", "");
    public LiveStreamController(ILiveStreamUseCase liveStreamUseCase)
    {
        _liveStreamUseCase = liveStreamUseCase;
    }

    [HttpGet("test")]
    public IActionResult Get()
    {
        return Ok(guid);
    }
    [HttpPost("register")]
    public async Task<IActionResult> RegisterLive([FromBody] LiveEntity live)
    {
        live.KeyAccessToken = guid;
        var registeredLive = await _liveStreamUseCase.RegisterAsync(live);
        return Ok(registeredLive.KeyAccessToken);
    }
    [HttpPost("start")]
    public async Task<IActionResult> StartLive([FromForm] string name) // nginx
    {
        Console.WriteLine("Rota de start");
        try
        {
            Console.WriteLine(name);
            var live = await _liveStreamUseCase.GetLatestLiveByKeyAccessAsync(name);
            await _liveStreamUseCase.StartAsync(live);
            return Ok(live.LiveId);
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            return BadRequest();
        }
    }
    [HttpPost("stop")]
    public async Task<IActionResult> StopLive([FromForm] string name) // nginx
    {
        Console.WriteLine("Rota de stop");
        var live = await _liveStreamUseCase.GetLatestLiveByKeyAccessAsync(name);
        await _liveStreamUseCase.StopAsync(live);
        return Ok();
    }
}