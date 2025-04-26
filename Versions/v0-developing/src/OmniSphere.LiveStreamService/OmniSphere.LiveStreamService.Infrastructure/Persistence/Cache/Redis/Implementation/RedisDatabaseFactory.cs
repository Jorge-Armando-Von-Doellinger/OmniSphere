using Microsoft.Extensions.Options;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Cache.Redis.Interface;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Cache.Redis.Settings;
using StackExchange.Redis;

namespace OmniSphere.LiveStreamService.Infrastructure.Persistence.Cache.Redis.Implementation;

public class RedisDatabaseFactory : IRedisDatabaseFactory
{
    private readonly RedisSettings _settings;

    public RedisDatabaseFactory(IOptions<RedisSettings> settings)
    {
        _settings = settings.Value;
    }

    public IDatabase GetDatabase()
    {
        return ConnectionMultiplexer
            .Connect(_settings.ConnectionString)
            .GetDatabase(_settings.DatabaseIndex);
    }
}