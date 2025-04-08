using MongoDB.Driver;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb.Interfaces;

namespace OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb;

public class MongoDbCollectionFactory : IMongoDbCollectionFactory
{
    private readonly MongoDbConnectionService _connectionService;

    public MongoDbCollectionFactory(MongoDbConnectionService connectionService)
    {
        _connectionService = connectionService;
    }

    public IMongoCollection<TEntity> GetCollection<TEntity>(string collectionName)
        => _connectionService
            .GetDatabase()
            .GetCollection<TEntity>(collectionName);
}