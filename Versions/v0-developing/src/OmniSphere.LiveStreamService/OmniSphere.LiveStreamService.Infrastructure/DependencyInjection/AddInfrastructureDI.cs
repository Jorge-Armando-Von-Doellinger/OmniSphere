using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using OmniSphere.LiveStreamService.Infrastructure.DependencyInjection.Settings;

namespace OmniSphere.LiveStreamService.Infrastructure.DependencyInjection;

public static class AddInfrastructureDI
{
    public static IServiceCollection AddInfrastructureLayerDI(this IServiceCollection services,
        IConfiguration configuration)
    {
        services
            .InjectSettingsLayer(configuration) // Mudar futuramente
            .AddDatabaseInjection();
        return services;
    }
}