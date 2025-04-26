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
        builder.Services.AddLayersDI(builder.Configuration);
        
        
        // Add layers DI - DependencyInjection/AddLayersDependencyInjection.cs
        builder.Configuration.AddYamlFile("application-prod.yaml", false, true);
        
        var app = builder.Build();

        app.UseMiddleware<ExceptionHandlerMiddleware>(); // Returns a personalized response, according to the exception thrown.
            
        // Configure the HTTP request pipeline.
        if (builder.Environment.IsDevelopment())
        {
            builder.Configuration.AddYamlFile("application-dev.yaml", false, true);
            app.MapOpenApi();
        }

        app.UseAuthorization();
        app.MapControllers();
        app.Run();
    }
}