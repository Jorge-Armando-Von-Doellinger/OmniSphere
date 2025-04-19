using StackExchange.Redis;

namespace OmniSphere.LiveStreamService.Infrastructure.Persistence.Cache.Redis.Interface;

public interface IRedisDatabaseFactory
{
    /// <summary>
    /// Generate a connection to Redis and retuns the cache database
    /// </summary>
    /// <returns> IDatabaseAsync - Cached </returns>
    IDatabase GetDatabase();
}