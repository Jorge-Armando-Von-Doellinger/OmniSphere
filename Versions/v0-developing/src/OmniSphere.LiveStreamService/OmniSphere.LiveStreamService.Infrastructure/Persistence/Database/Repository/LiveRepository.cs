using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Options;
using MongoDB.Driver;
using OmniSphere.LiveStreamService.Core.Entity;
using OmniSphere.LiveStreamService.Core.Interfaces.Repository;
using OmniSphere.LiveStreamService.Infrastructure.Exceptions;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb.Interfaces;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb.Settings;

namespace OmniSphere.LiveStreamService.Infrastructure.Implementations.Repository;

public class LiveRepository : ILiveRepository
{
    private readonly IMongoCollection<LiveEntity> _collection;

    public LiveRepository(IMongoDbCollectionFactory collectionFactory,
        IOptions<MongoDbSettings> settings)
    {
        var collectionName = settings.Value.CollectionName ?? throw new InvalidDatabaseSettingsException("Collection name is required");
        _collection = collectionFactory.GetCollection<LiveEntity>(settings.Value.CollectionName);
    }
    public async Task<LiveEntity> GetLatestByUsername(string username)
    {
        var docs = await _collection.FindAsync(entity => entity.Username == username);
        var lives = await docs.ToListAsync();
        var last = lives.LastOrDefault();
        return last;
    }

    public async Task<LiveEntity> GetById(string liveId)
    {
        var docs = await _collection.FindAsync(entity => entity.LiveId == liveId);
        return await docs.FirstOrDefaultAsync();
    }

    public async Task Add(LiveEntity live)
    {
        await _collection.InsertOneAsync(live);
    }

    public async Task Remove(string liveId)
    {
        await _collection.DeleteOneAsync(entity => entity.LiveId == liveId);
    }
}