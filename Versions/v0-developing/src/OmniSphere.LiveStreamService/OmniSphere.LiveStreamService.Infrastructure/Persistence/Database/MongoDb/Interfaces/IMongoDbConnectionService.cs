using MongoDB.Driver;

namespace OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb.Interfaces;

public interface IMongoDbConnectionService
{
    IMongoDatabase GetDatabase();
}