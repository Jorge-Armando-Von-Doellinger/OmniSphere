using System.Text.Json;
using System.Text.Json.Serialization;
using OmniSphere.LiveStreamService.Core.Entity;
using OmniSphere.LiveStreamService.Core.Interfaces.Repository;
using OmniSphere.LiveStreamService.Infrastructure.Exceptions;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Cache.Redis.Interface;
using StackExchange.Redis;

namespace OmniSphere.LiveStreamService.Infrastructure.Persistence.Cache.Redis.Repository;

public class KeyAccessCachedRepository : ICachedKeyAccessRepository
{
    private readonly IDatabase _database;

    public KeyAccessCachedRepository(IRedisDatabaseFactory databaseFactory)
    {
        _database = databaseFactory.GetDatabase();
    }
    public async Task AddAsync(KeyAccessEntity keyAccess)
    {
        var json = JsonSerializer.Serialize(keyAccess);
        await _database.StringSetAsync(keyAccess.KeyAccess, json, keyAccess.ExpireIn.TimeOfDay);
    }

    public async Task ExtendAsync(string keyAccess, int durationInMinutes)
    {
        string value = await _database.StringGetAsync(keyAccess);
        await _database.StringSetAsync(keyAccess, value, TimeSpan.FromMinutes(durationInMinutes));
    }

    public async Task RemoveAsync(string keyAccess)
    {
        await _database.KeyDeleteAsync(keyAccess);
    }

    public async Task<KeyAccessEntity> GetByKeyAccess(string keyAccess)
    {
        var json = await _database.StringGetAsync(keyAccess);
        return JsonSerializer.Deserialize<KeyAccessEntity>(json) ?? throw new FailedToConvertJsonToEntity();
    }
    
    public async Task<List<KeyAccessEntity>> GetAllByUserIdAsync(string userId)
    {
        throw new NotImplementedException();
    }

    public async Task<bool> ExistsAsync(string keyAccess, string userId)
    {
        var exists = await _database.KeyExistsAsync(keyAccess);
        return exists;
    }

    public async Task ClearCacheAsync()
    {
        throw new NotImplementedException();
    }
}