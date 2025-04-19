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

    public async Task<LiveEntity> GetLatestByKeyAccessAsync(string keyAccess)
    {
        var docs = await _collection.FindAsync(x => x.KeyAccessToken == keyAccess);
        var latest = await docs.FirstOrDefaultAsync() ?? throw new EntityNotFoundException("Live", keyAccess);
        return latest;
    }

    public async Task<LiveEntity> GetByIdAsync(string liveId)
    {
        var docs = await _collection.FindAsync(entity => entity.LiveId == liveId);
        return await docs.FirstOrDefaultAsync() ?? throw new EntityNotFoundException("Live", liveId);
    }

    public async Task AddAsync(LiveEntity live)
    {
        await _collection.InsertOneAsync(live);
    }

    public async Task UpdateAsync(LiveEntity live)
    {
        var latest = await this.GetLatestByKeyAccessAsync(live.KeyAccessToken); // Its already does the validation, where it returns an exception case null!
        latest.FinalizedAt = live.FinalizedAt; 
        latest.Description = live.Description;
        latest.Title = live.Title;
        latest.StartedAt = live.StartedAt;
        await _collection.ReplaceOneAsync(x => x.LiveId == live.LiveId, live);
    }

    public async Task RemoveAsync(string liveId)
    {
        await _collection.DeleteOneAsync(entity => entity.LiveId == liveId);
    }
}