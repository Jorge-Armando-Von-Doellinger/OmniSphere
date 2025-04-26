namespace OmniSphere.LiveStreamService.Core.Exceptions;

public class KeyAccessExpiredException : Exception
{
    public KeyAccessExpiredException() : base("Key access expired! Please, generate a new one and try again.")
    {
    }

    public KeyAccessExpiredException(string message) : base(message)
    {
    }
}