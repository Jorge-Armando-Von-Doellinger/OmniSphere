namespace OmniSphere.LiveStreamService.Core.Interfaces.Services;

public interface ILiveStreamService
{
    // Este serviço será implementado na infrastructure
    // Devido a dependencia externa ao ffmpeg e ao caminho destino
    // da live no container (ou aplicação)
    Task<string> StartAsync(string username, string title);
    Task StopAsync(string liveId); 
    Task<Stream> GetLiveStreamByIdAsync(string liveId);
}