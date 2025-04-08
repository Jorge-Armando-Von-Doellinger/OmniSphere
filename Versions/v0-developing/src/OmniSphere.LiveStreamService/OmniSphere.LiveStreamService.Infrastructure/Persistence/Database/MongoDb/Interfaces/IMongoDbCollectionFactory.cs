using MongoDB.Driver;

namespace OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb.Interfaces;

public interface IMongoDbCollectionFactory
{
    IMongoCollection<TEntity> GetCollection<TEntity>(string collectionName);
}