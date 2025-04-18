using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Services.Processors.Settings;

namespace OmniSphere.LiveStreamService.Infrastructure.DependencyInjection.Settings;

internal static class StorageSettingsInjection
{
    internal static IServiceCollection AddStorageSettingsInjection(this IServiceCollection services,
        IConfiguration configuration)
    {
        services.Configure<LiveStorageSettings>(configuration.GetSection("Storage:PhysicalStorage"));
        return services;
    }
}