using OmniSphere.LiveStreamService.Core.Entity;
using OmniSphere.LiveStreamService.Core.Interfaces.Services;

namespace OmniSphere.LiveStreamService.Infrastructure.Persistence.Services.Processors.Implementations;

public class LiveProcessor : ILiveProcessor // ILiveProcessor -> Core 
{
    // Vai se comunicar com o FFmpeg (do container)
    // Irá utilizar o HlsPath para coletar os hls correspondentes a live
    // Após, irá se comunicar com o FFmpeg para converte-lo em mp4
    public LiveProcessor() // Configs,
    {
        
    }
    public Task Process(LiveEntity live)
    {
        throw new NotImplementedException();
    }
}