using Microsoft.Extensions.DependencyInjection;
using OmniSphere.LiveStreamService.Core.Interfaces.Repository;
using OmniSphere.LiveStreamService.Infrastructure.Implementations.Repository;
using OmniSphere.LiveStreamService.Infrastructure.Mapper;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb.Interfaces;

namespace OmniSphere.LiveStreamService.Infrastructure.DependencyInjection;

public static class DatabaseInjection
{
    internal static IServiceCollection AddDatabaseInjection(this IServiceCollection services)
    {
        services
            .AddMongoDbServices()
            .AddRepositories();
        return services;
    }

    private static IServiceCollection AddRepositories(this IServiceCollection services)
    {
        services.AddScoped<ILiveRepository, LiveRepository>();
        return services;
    }
    private static IServiceCollection AddMongoDbServices(this IServiceCollection services) // Persistence.Database
    {
        MongoDbClassMapService.Initialize(); // Mapeia o LiveEntity - aceito sugestões de lugares melhores para colocar
        services.AddScoped<IMongoDbCollectionFactory, MongoDbCollectionFactory>();
        services.AddScoped<IMongoDbConnectionService, MongoDbConnectionService>();
        return services;
    }
    
}