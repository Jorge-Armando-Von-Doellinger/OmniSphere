namespace OmniSphere.LiveStreamService.Core.Interfaces.Repository;

public interface ICachedKeyAccessRepository : IKeyAccessRepository
{
    Task<bool> ExistsAsync(string keyAccess, string userId); // It only doesn't exists if it's expired
    Task ClearCacheAsync(); // Clear the cache
}