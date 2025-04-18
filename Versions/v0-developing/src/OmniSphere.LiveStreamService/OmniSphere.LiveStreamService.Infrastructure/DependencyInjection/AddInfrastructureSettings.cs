using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace OmniSphere.LiveStreamService.Infrastructure.DependencyInjection.Settings;

internal static class AddInfrastructureSettings
{
    internal static IServiceCollection InjectSettingsLayer(this IServiceCollection services, IConfiguration configuration)
    {
        services.AddDatabaseSettingsDI(configuration);
        services.AddStorageSettingsInjection(configuration);
        return services;
    }
}