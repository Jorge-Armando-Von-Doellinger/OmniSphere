namespace OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb.Settings;

public class MongoDbSettings
{
    public string ConnectionString { get; set; }
    public string DatabaseName { get; set; }
    public string CollectionName { get; set; }
}