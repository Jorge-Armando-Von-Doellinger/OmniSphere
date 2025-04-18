using Microsoft.Extensions.DependencyInjection;
using OmniSphere.LiveStreamService.Core.Interfaces.Services;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Services.Processors.Implementations;
using OmniSphere.LiveStreamService.Infrastructure.Services.Converter.Implementations;

namespace OmniSphere.LiveStreamService.Infrastructure.DependencyInjection;

internal static class ProcessorInjection
{
    internal static IServiceCollection AddProcessorInjection(this IServiceCollection services)
    {
        
        services.AddScoped<ILiveProcessor, LiveProcessor>();
        return services;
    }
}