using MongoDB.Driver;
using OmniSphere.LiveStreamService.Infrastructure.Exceptions;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb.Interfaces;

namespace OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb;

public class MongoDbCollectionFactory : IMongoDbCollectionFactory
{
    private readonly IMongoDbConnectionService _connectionService;

    public MongoDbCollectionFactory(IMongoDbConnectionService connectionService)
    {
        _connectionService = connectionService;
    }

    public IMongoCollection<TEntity> GetCollection<TEntity>(string collectionName)
    {
        try
        {
            return _connectionService
                .GetDatabase()
                .GetCollection<TEntity>(collectionName);
        }
        catch (Exception e)
        {
            throw new FailedToConnectOnDatabaseException();
        }
    }
}