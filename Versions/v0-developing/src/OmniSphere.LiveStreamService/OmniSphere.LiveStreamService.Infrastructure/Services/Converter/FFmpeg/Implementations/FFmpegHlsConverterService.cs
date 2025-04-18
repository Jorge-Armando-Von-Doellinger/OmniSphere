using System.Diagnostics;
using Microsoft.Extensions.Options;
using OmniSphere.LiveStreamService.Infrastructure.Exceptions;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Services.FFmpeg;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Services.Processors.Settings;

namespace OmniSphere.LiveStreamService.Infrastructure.Services.Converter.Implementations;

public class FFmpegHlsConverterService : IHlsConverter
{
    private readonly LiveStorageSettings _settings;

    public FFmpegHlsConverterService(IOptions<LiveStorageSettings> settings)
    {
        _settings = settings.Value;
    }
    public async Task ConvertHls(string liveId)
    {
        try
        {
            var command = await GetConvertCommand(liveId);
            await ExecuteAsync(command);
            await Task.Delay(1000); 
            // await ClearHlsFiles(liveId); - Cause error in conversion | fragment.ts[] -> m4p
        }
        catch (Exception ex)
        {
            Console.WriteLine("Error "+ex.Message);
            throw ex; 
        }
    }
    
    private async Task ExecuteAsync(string command)
    {
        var processStartInfo = new ProcessStartInfo()
        {
            FileName = "ffmpeg",
            Arguments = command,
            RedirectStandardOutput = true,
            RedirectStandardError = true,
            UseShellExecute = false,
            CreateNoWindow = true
        };
        using (var process = new Process() { StartInfo = processStartInfo})
        {
            process.Start();
            await process.WaitForExitAsync();
            var error = await process.StandardError.ReadToEndAsync();
            if(process.ExitCode != 0) throw new FailedToConvertHlsException(error);
        }
    }
    private IEnumerable<string> GetLiveFiles(string liveId)
    {
        var tsFiles = Directory.GetFiles(_settings.BaseHlsPath)
            .Where(f => f.Contains(liveId));
        return tsFiles;
    } 

    /*private IEnumerable<string> GetLiveTsFiles(string liveId)
    {
        var tsFiles = Directory.GetFiles(_settings.BaseHlsPath)
            .Where(f => f.EndsWith(".ts") && f.Contains(liveId))
            .Select(f => new { 
                Path = f, 
                Number = int.Parse(Path.GetFileNameWithoutExtension(f).Split("-").Last())})
            .OrderBy(n => n.Number)
            .Select(x => x.Path)
            .ToList();
        return tsFiles;
    }*/ 
    private async Task<string> GetConvertCommand(string liveId)
    {
        var hlsPath = Path.Combine(_settings.BaseHlsPath, $"{liveId}.m3u8");
        var mp4Path = Path.Combine(_settings.BaseVideoPath, $"{liveId}.mp4");
        var command = $"-y -fflags +genpts+discardcorrupt -y -i \"{hlsPath}\" -c:v copy -c:a aac -f mp4 \"{mp4Path}\"";
        return command;
    }
    private async Task ClearHlsFiles(string liveId)
    {
        var tsFiles = GetLiveFiles(liveId);
        foreach (var file in tsFiles)
        {
            File.Delete(file);
        }
    }
}
