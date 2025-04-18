namespace OmniSphere.LiveStreamService.Infrastructure.Exceptions;

public class LiveMetadataNotFoundedException : Exception
{
    public LiveMetadataNotFoundedException(string message) : base(message)
    {
        
    }

    public LiveMetadataNotFoundedException() : base("Live metadata cannot be founded!")
    {
        
    }
}