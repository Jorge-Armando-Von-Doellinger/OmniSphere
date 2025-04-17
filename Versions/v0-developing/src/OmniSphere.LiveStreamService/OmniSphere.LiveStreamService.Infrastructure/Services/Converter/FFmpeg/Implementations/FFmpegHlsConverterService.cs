using System.Diagnostics;
using OmniSphere.LiveStreamService.Infrastructure.Exceptions;
using OmniSphere.LiveStreamService.Infrastructure.Persistence.Services.FFmpeg;

namespace OmniSphere.LiveStreamService.Infrastructure.Services.Converter.Implementations;

public class FFmpegHlsConverterService : IHlsConverter
{
    
    public async Task ConvertHls(string hlsPath, string videoPath)
    {
        try
        {
            var command = GetConvertCommand(hlsPath, videoPath);
            await Execute(command);
        }
        catch
        {
            throw new FailedToConvertHlsException(); 
        }
    }


    private async Task Execute(string command)
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
            var error = await process.StandardError.ReadToEndAsync();
            await process.WaitForExitAsync();
            if(process.ExitCode != 0) throw new FailedToConvertHlsException();
        }
    }

    private string GetConvertCommand(string hlsPath, string videoPath)
    {
        return $"-i \"{hlsPath}\" -c:v copy -c:a aac -f mp4 \"{videoPath}\"";
    }
    
}
