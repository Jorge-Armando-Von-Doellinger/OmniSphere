using OmniSphere.LiveStreamService.Core.Entity;

namespace OmniSphere.LiveStreamService.Application.UseCases.Interfaces;

public interface IKeyAccessUseCase
{
    /// <summary>
    /// Generate an access key for a live account and have an expiration date - by security 
    /// </summary>
    /// <param name="durationInDays"></param>
    /// <param name="userId"></param>
    /// <returns> KeyAccessEntity </returns>
    Task<KeyAccessEntity> GenerateKeyAccessAsync(int durationInDays, string userId);
    /// <summary>
    /// Generate an access key for a live account and have a short expiration time to use (max: 30 min)  - by security 
    /// </summary>
    /// <param name="durationInMinutes"></param>
    /// <param name="userId"></param>
    /// <returns> KeyAccessEntity </returns>
    Task<KeyAccessEntity> GenerateTemporaryKeyAccessAsync(int durationInMinutes, string userId);
    Task<bool> ValidateKeyAccessAsync(string keyAccess, string userId);
    Task<bool> ValidateTemporaryKeyAccessAsync(string keyAccess, string userId);
}