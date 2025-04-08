using OmniSphere.LiveStreamService.API.DependencyInjection;

namespace OmniSphere.LiveStreamService.API;

public class Program
{
    public static void Main(string[] args)
    {
        var builder = WebApplication.CreateBuilder(args);

        // Add services to the container.

        builder.Services.AddControllers();
        // Learn more about configuring OpenAPI at https://aka.ms/aspnet/openapi
        builder.Services.AddOpenApi(); // Swagger
        builder.Services.AddLayersDI(); // Add layers DI - DependencyInjection/AddLayersDependencyInjection.cs
        builder.Configuration.AddYamlFile("application-prod.yaml", optional: false, reloadOnChange: true);
        builder.Configuration.AddYamlFile($"application-dev.yaml", optional: true, reloadOnChange: true);

        
        var app = builder.Build();
        // Configure the HTTP request pipeline.
        if (builder.Environment.IsDevelopment()) app.MapOpenApi();
        
        app.UseAuthorization();
        app.MapControllers();
        app.Run();
    }
}