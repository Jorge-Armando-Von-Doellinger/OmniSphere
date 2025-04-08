using MongoDB.Bson.Serialization;
using OmniSphere.LiveStreamService.Core.Entity;

namespace @OmniSphere.LiveStreamService.Infrastructure.Mapper;

public static class MongoDbClassMapService
{
    public static void Initialize()
    {
        MapLiveEntity();
    }
    
    private static void MapLiveEntity()
    {
        var registered = BsonClassMap.IsClassMapRegistered(typeof(LiveEntity));
        if(registered) return;
        BsonClassMap.RegisterClassMap<LiveEntity>(x =>
        {
            x.AutoMap();
            x.MapIdField(x => x.LiveId);
        });
    }
}