using Microsoft.AspNetCore.Mvc;
using OmniSphere.LiveStreamService.API.Exceptions;
using OmniSphere.LiveStreamService.Application.UseCases.Interfaces;

namespace OmniSphere.LiveStreamService.API.Controllers;

[ApiController]
[Route("/api/key-access")]
public class KeyAccessController : ControllerBase
{
    private readonly IKeyAccessUseCase _keyAccessUseCase;
    public KeyAccessController(IKeyAccessUseCase keyAccessUseCase)
    {
        _keyAccessUseCase = keyAccessUseCase;
    }
    
    
    [HttpPost("temporary")]
    public async Task<IActionResult> GetTemporaryKeyAccess()
    {
        var userId = Request.Headers["Authorization"].FirstOrDefault();
        if(string.IsNullOrEmpty(userId) || userId == null) throw new RequestHeaderNotFounded("Authorization", "This header is required to authenticate user and generate a key access.");
        // operations above it will be executed in the middleware, where it do a authenticates the user
        
        var key = await _keyAccessUseCase.GenerateTemporaryKeyAccessAsync(30, userId);
        return Ok("Key access successful ly generated! This key will expire in 30 minutes.");
    }
    [HttpPost("current")]
    public async Task<IActionResult> GetKeyAccess(DateTime? expirationDate)
    {
        var days = expirationDate.HasValue 
            ? (int) (expirationDate.Value - DateTime.Now).TotalDays 
            : 30;
        var userId = Request.Headers["Authorization"].FirstOrDefault();
        if(string.IsNullOrEmpty(userId) || userId == null) throw new RequestHeaderNotFounded("Authorization", "This header is required to authenticate user and generate a key access.");
        // operations above it will be executed in the middleware, where it do a authenticates the user
        
        var key = await _keyAccessUseCase.GenerateKeyAccessAsync(days, userId);
        return Ok($"Key access successful ly generated! This key will expire in {days} days.");
    }
}