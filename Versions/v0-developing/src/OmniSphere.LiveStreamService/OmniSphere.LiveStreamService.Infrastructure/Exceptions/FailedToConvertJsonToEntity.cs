namespace OmniSphere.LiveStreamService.Infrastructure.Exceptions;

public class FailedToConvertJsonToEntity : Exception
{
    public FailedToConvertJsonToEntity() : base("Failed to convert json to entity")
    {
        
    }

    public FailedToConvertJsonToEntity(string message) : base(message)
    {
        
    }
}