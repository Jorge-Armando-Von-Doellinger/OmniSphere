using Microsoft.Extensions.DependencyInjection;
using OmniSphere.LiveStreamService.Application.UseCases.Implementations;
using OmniSphere.LiveStreamService.Application.UseCases.Interfaces;

namespace OmniSphere.LiveStreamService.Application.DependencyInjection;

internal static class UseCasesInjection
{
    internal static IServiceCollection AddUseCasesInjection(this IServiceCollection services)
    {
        services.AddScoped<ILiveStreamUseCase, LiveStreamUseCase>();
        return services;
    }
}