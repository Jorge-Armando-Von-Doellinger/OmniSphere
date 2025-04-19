using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Cache.Redis.Settings;

namespace OmniSphere.LiveStreamService.Infrastructure.DependencyInjection.Settings;

internal static class CacheDatabaseSettingsInjection
{
    internal static IServiceCollection AddCacheDatabaseSettingsDI(this IServiceCollection services,
        IConfiguration configuration)
    {
        services.Configure<RedisSettings>(configuration.GetSection("Cache:Redis")); //
        return services;
    }
}