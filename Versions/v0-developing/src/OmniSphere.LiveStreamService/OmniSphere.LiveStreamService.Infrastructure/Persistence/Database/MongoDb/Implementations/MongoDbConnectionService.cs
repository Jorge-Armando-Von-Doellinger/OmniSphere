using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Options;
using MongoDB.Driver;
using OmniSphere.LiveStreamService.Core.Entity;
using OmniSphere.LiveStreamService.Infrastructure.Exceptions;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb.Interfaces;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb.Settings;

namespace OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb;

public class MongoDbConnectionService : IMongoDbConnectionService
{
    private readonly IConfiguration _configuration;

    private readonly MongoDbSettings _settings;

    // Responsavel pela conexão com o MongoDb
    // Possui metodos para pegar as configurações
    public MongoDbConnectionService(IConfiguration configuration, 
        IOptionsMonitor<MongoDbSettings> settings)
    {
        _configuration = configuration;
        _settings = settings.CurrentValue;
        Console.WriteLine(GetDatabaseName());
        Console.WriteLine(GetConnectionString());
    }

    public IMongoDatabase GetDatabase()
    {
        try
        {
            return new MongoClient(this.GetConnectionString())
                .GetDatabase(this.GetDatabaseName());
        }
        catch
        {
            throw new FailedToConnectOnDatabaseException();
        }
    }
        
    private string GetConnectionString()
        => _settings.ConnectionString ?? throw new InvalidDatabaseSettingsException("Connection string is missing.");

    private string GetDatabaseName()
        => _settings.DatabaseName ?? throw new InvalidDatabaseSettingsException("Database name is missing.");
}