using Microsoft.Extensions.DependencyInjection;

namespace OmniSphere.LiveStreamService.Application.DependencyInjection;

public static class AddApplicationDI
{
    public static IServiceCollection AddApplicationLayerDI(this IServiceCollection services)
    {
        services.AddUseCasesInjection();
        return services;
    }
}