namespace OmniSphere.LiveStreamService.Infrastructure.Persistence.Services.Processors.Settings;

public class LivePhysicalStorageSettings
{
    public string BaseHlsPath { get; set; } // Ex: /app/hls - container
    public string BaseVideoPath { get; set; } // Ex: /app/video - container
    public string StorageType { get; set; } // Ex: Cloud or Physical
}