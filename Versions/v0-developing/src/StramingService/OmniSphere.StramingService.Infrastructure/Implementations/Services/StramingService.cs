using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using OmniSphere.StramingService.Core.Interfaces.Services;

namespace OmniSphere.StramingService.Infrastructure.Implementations.Services
{
    public class StramingService : IStreamingService
    {
        public Task StartStreaming(string username, string liveName)
        {
            throw new NotImplementedException();
        }

        public Task StopStreaming(string username, string liveName)
        {
            throw new NotImplementedException();
        }
    }
}
