using Microsoft.Extensions.Configuration;
using MongoDB.Driver;
using OmniSphere.LiveStreamService.Core.Entity;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb.Interfaces;

namespace OmniSphere.LiveStreamService.Infrastructure.Persistence.Database.MongoDb;

public class MongoDbConnectionService : IMongoDbConnectionService
{
    private readonly IConfiguration _configuration;
    // Responsavel pela conexão com o MongoDb
    // Possui metodos para pegar as configurações
    public MongoDbConnectionService(IConfiguration configuration)
    {
        _configuration = configuration;
        Console.WriteLine(GetDatabaseName());
        Console.WriteLine(GetConnectionString());
    }
    public IMongoDatabase GetDatabase()
        => new MongoClient(this.GetConnectionString())
            .GetDatabase(this.GetDatabaseName());
    private string GetConnectionString()
        => _configuration.GetConnectionString("Database:MongoDb:CONNECTION_STRING");

    private string GetDatabaseName()
        => _configuration.GetSection("Database:MongoDb:DATABASE_NAME").Value;
}