using OmniSphere.LiveStreamService.Core.Entity;
using OmniSphere.LiveStreamService.Core.Interfaces.Services;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Services.FFmpeg;

namespace OmniSphere.LiveStreamService.Infrastructure.Services.Processors.Implementations;

public class LiveProcessor : ILiveProcessor // ILiveProcessor -> Core 
{
    private readonly IHlsConverter _converter;

    // Vai se comunicar com o FFmpeg (do container)
    // Irá utilizar o HlsPath para coletar os hls correspondentes a live
    // Após, irá se comunicar com o FFmpeg para converte-lo em mp4
    public LiveProcessor(IHlsConverter converter)
    {
        _converter = converter;
    }
    public async Task Process(LiveEntity live)
    {
        await _converter.ConvertHls(live.KeyAccess);
    }
}