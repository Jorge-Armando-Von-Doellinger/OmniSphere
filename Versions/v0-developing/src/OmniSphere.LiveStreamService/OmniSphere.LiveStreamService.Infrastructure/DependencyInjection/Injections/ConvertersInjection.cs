using Microsoft.Extensions.DependencyInjection;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Services.FFmpeg;
using OmniSphere.LiveStreamService.Infrastructure.Services.Converter.Implementations;

namespace OmniSphere.LiveStreamService.Infrastructure.DependencyInjection;

internal static class ConvertersInjection
{
    internal static IServiceCollection AddConvertersInjection(this IServiceCollection services)
    {
        services.AddScoped<IHlsConverter, FFmpegHlsConverterService>();
        return services;
    }
}