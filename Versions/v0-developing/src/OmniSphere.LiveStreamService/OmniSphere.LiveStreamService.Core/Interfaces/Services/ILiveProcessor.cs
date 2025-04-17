using OmniSphere.LiveStreamService.Core.Entity;

namespace OmniSphere.LiveStreamService.Core.Interfaces.Services;

public interface ILiveProcessor
{
    Task Process(LiveEntity live);
}