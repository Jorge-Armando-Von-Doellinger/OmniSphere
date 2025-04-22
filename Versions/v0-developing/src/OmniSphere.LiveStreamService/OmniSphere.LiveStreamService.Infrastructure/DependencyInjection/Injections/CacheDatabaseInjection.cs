using Microsoft.Extensions.DependencyInjection;
using OmniSphere.LiveStreamService.Core.Interfaces.Repository;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Cache.Redis.Implementation;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Cache.Redis.Interface;

namespace OmniSphere.LiveStreamService.Infrastructure.DependencyInjection;

internal static class CacheDatabaseInjection
{
    internal static IServiceCollection AddCacheDatabaseInjection(this IServiceCollection services)
    {
        services
            .AddFactories();
        return services;
    }
    
    private static IServiceCollection AddFactories(this IServiceCollection services)
    {
        services.AddScoped<IRedisDatabaseFactory>();
        return services;
    }
}