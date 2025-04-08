using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace OmniSphere.LiveStreamService.Infrastructure.DependencyInjection;

public static class AddInfrastructureDI
{
    public static IServiceCollection AddInfrastructureLayerDI(this IServiceCollection services)
    {
        services.AddDatabaseInjection();
        return services;
    }
    
}