namespace OmniSphere.LiveStreamService.Infrastructure.Persistence.Services.FFmpeg;

public interface IHlsConverter
{
    /// <summary>
    /// Find all files in default storage (physical or cloud) and compare file name with liveId!
    /// Because, all files they are saved on the liveID. Ex: ${path}/{liveId}-{n}.ts
    /// </summary>
    /// <param name="liveId"> live unique identifier</param>
    Task ConvertHls(string liveId);
}