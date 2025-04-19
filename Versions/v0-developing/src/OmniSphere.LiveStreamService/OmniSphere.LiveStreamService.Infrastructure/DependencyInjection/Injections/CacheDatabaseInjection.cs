using Microsoft.Extensions.DependencyInjection;
using OmniSphere.LiveStreamService.Core.Interfaces.Repository;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Cache.Redis.Implementation;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Cache.Redis.Interface;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Cache.Redis.Repository;

namespace OmniSphere.LiveStreamService.Infrastructure.DependencyInjection;

internal static class CacheDatabaseInjection
{
    internal static IServiceCollection AddCacheDatabaseInjection(this IServiceCollection services)
    {
        services
            .AddFactories()
            .AddRepositories();
        return services;
    }

    private static IServiceCollection AddRepositories(this IServiceCollection services)
    {
        services.AddScoped<ICachedKeyAccessRepository, KeyAccessCachedRepository>();
        return services;
    }
    
    private static IServiceCollection AddFactories(this IServiceCollection services)
    {
        services.AddScoped<IRedisDatabaseFactory, RedisDatabaseFactory>();
        return services;
    }
}