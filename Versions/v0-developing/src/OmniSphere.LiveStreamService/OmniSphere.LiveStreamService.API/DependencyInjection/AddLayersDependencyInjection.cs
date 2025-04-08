using OmniSphere.LiveStreamService.Infrastructure.DependencyInjection;

namespace OmniSphere.LiveStreamService.API.DependencyInjection;

public static class AddLayersDependencyInjection
{
    public static IServiceCollection AddLayersDI(this IServiceCollection services)
    {
        services.AddInfrastructureLayerDI();
        return services;
    }
}