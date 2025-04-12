using System.Diagnostics;
using Microsoft.Extensions.Options;
using OmniSphere.LiveStreamService.Core.Entity;
using OmniSphere.LiveStreamService.Core.Interfaces.Services;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Storage.Settings;

namespace OmniSphere.LiveStreamService.Infrastructure.Persistence.Storage.Implementations.Dev;

public class FFmpegLiveStreamService : ILiveStreamService, IAsyncDisposable
{
    private readonly string sourceConfig = "x11grab -i :0.0+0,0 -f alsa -i hw:0"; // Temporario
    private readonly string _rtmpUrl;
    private static readonly Dictionary<string, Process> _ffmpegProcesses = new();
    // Service que vai abstrair o FFmpeg
    public FFmpegLiveStreamService(IOptions<StorageSettings> storageSettings)
    {
        _rtmpUrl = storageSettings.Value.HlsUrl;
    }
    
    public async Task StartAsync(LiveEntity live)
    {
        var livePath = $"{live.Username}/{live.Username}";
        var process = new Process()
        {
            StartInfo = new()
            {
                FileName = "ffmpeg",
                Arguments = $"-f {sourceConfig} -c:v libx264 -c:a aac -f flv {_rtmpUrl}/{livePath}",
                RedirectStandardOutput = true,
                RedirectStandardError = true,
                UseShellExecute = false,
                CreateNoWindow = true
            }
        };
        process.Start();
        _ffmpegProcesses.Add(live.LiveId, process);
        await Task.CompletedTask;
    }

    public async Task StopAsync(LiveEntity live)
    {
        var liveId = live.LiveId;
        if (_ffmpegProcesses.TryGetValue(liveId, out var process))
        {
            process.Kill();
            await process.WaitForExitAsync();
            _ffmpegProcesses.Remove(liveId);
        }
        await Task.CompletedTask;
    }
    
    public async ValueTask DisposeAsync()
    {
        foreach (var process in _ffmpegProcesses)
        {
            process.Value.Kill();
        }
    }


}