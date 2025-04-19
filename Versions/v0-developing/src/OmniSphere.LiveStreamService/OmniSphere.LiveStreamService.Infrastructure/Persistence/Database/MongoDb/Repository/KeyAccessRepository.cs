using Microsoft.Extensions.Options;
using MongoDB.Driver;
using OmniSphere.LiveStreamService.Core.Entity;
using OmniSphere.LiveStreamService.Core.Interfaces.Repository;
using OmniSphere.LiveStreamService.Infrastructure.Exceptions;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb.Interfaces;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb.Settings;

namespace OmniSphere.LiveStreamService.Infrastructure.Implementations.Repository;

public class KeyAccessRepository : IKeyAccessRepository
{
    private readonly IMongoCollection<KeyAccessEntity> _collection;

    public KeyAccessRepository(IMongoDbCollectionFactory collectionFactory,
        IOptionsMonitor<MongoDbSettings> settings)
    {
        _collection = collectionFactory
            .GetCollection<KeyAccessEntity>(settings.CurrentValue.CollectionName);
    }
    public async Task AddAsync(KeyAccessEntity keyAccess)
    {
        var exists = await (await _collection.FindAsync(x => x.KeyAccess == keyAccess.KeyAccess))
            .AnyAsync();
        if(exists) throw new EntityAlreadyExistsException("KeyAccess", keyAccess.KeyAccess);
        await _collection.InsertOneAsync(keyAccess);
    }

    public async Task ExtendAsync(string keyAccess, int durationInMinutes)
    {
        var key = await this.GetByKeyAccess(keyAccess); // Its already does the validation, where it returns an exception case null! 
        key.SetExpireIn(durationInMinutes);
        await _collection.ReplaceOneAsync(x => x.KeyAccess == keyAccess, key);
    }

    public async Task RemoveAsync(string keyAccess)
    {
        await _collection.DeleteOneAsync(x => x.KeyAccess == keyAccess);       
    }

    public async Task<KeyAccessEntity> GetByKeyAccess(string keyAccess)
    {
        var docs = await _collection
            .FindAsync(x => x.KeyAccess == keyAccess);
        var key = await docs.FirstOrDefaultAsync() ?? throw new EntityNotFoundException("Live", keyAccess);
        return key;
    }

    public async Task<List<KeyAccessEntity>> GetAllByUserIdAsync(string userId)
    {
        var docs = await _collection
            .FindAsync(x => x.UserId == userId);
        var keys = await docs.ToListAsync();
        return keys;
    }
}