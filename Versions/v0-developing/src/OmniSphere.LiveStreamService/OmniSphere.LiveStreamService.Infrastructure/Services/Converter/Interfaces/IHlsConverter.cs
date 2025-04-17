namespace OmniSphere.LiveStreamService.Infrastructure.Persistence.Services.FFmpeg;

public interface IHlsConverter
{
    Task ConvertHls(string hlsPath, string videoPath);
}