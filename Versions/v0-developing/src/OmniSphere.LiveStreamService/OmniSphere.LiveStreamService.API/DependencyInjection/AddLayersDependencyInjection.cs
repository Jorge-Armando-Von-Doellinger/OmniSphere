using OmniSphere.LiveStreamService.Application.DependencyInjection;
using OmniSphere.LiveStreamService.Infrastructure.DependencyInjection;

namespace OmniSphere.LiveStreamService.API.DependencyInjection;

public static class AddLayersDependencyInjection
{
    public static IServiceCollection AddLayersDI(this IServiceCollection services, IConfiguration configuration)
    {
        services.AddApplicationLayerDI();
        services.AddInfrastructureLayerDI(configuration);
        return services;
    }
}