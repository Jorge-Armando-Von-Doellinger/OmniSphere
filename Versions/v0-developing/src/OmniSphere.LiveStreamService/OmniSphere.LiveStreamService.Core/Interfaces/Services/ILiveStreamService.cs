using OmniSphere.LiveStreamService.Core.Entity;

namespace OmniSphere.LiveStreamService.Core.Interfaces.Services;

public interface ILiveStreamService
{
    // Este serviço será implementado na infrastructure
    // Devido a dependencia externa ao ffmpeg e ao caminho destino
    // da live no container (ou aplicação)
    Task StartAsync(LiveEntity live);
    Task StopAsync(LiveEntity live); 
    
    // O NGINX-RTMP que cuidará de armazenar a live, de boas...
}