using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb.Settings;

namespace OmniSphere.LiveStreamService.Infrastructure.DependencyInjection.Settings;

internal static class DatabaseSettingsInjection
{
    internal static IServiceCollection AddDatabaseSettingsDI(this IServiceCollection services,
        IConfiguration configuration)
    {
        services.Configure<MongoDbSettings>(configuration.GetSection("Database:MongoDb"));
        return services;
    }
}