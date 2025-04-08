using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OmniSphere.StramingService.Core.Interfaces.Services
{
    public interface IStreamingService // Será implementado na infrastructure, pois vai se comunicar com o disco local ou numve futuramente
    {
        Task StartStreaming(string username, string liveName);
        Task StopStreaming(string username, string liveName);
    }
}
