namespace OmniSphere.LiveStreamService.Infrastructure.Exceptions;

public class FailedToConvertHlsException : Exception
{
    public FailedToConvertHlsException() : base("Failed to parse hls to mp4 format")
    {
        
    }

    public FailedToConvertHlsException(string message) : base(message)
    {
        
    }
}