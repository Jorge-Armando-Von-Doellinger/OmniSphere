namespace OmniSphere.LiveStreamService.API.Exceptions;

public class RequestHeaderNotFounded : Exception
{
    public RequestHeaderNotFounded(string message) : base(message)
    {
    }

    public RequestHeaderNotFounded(string header, Exception ex) : base(GetMessage(header), ex)
    {
    }

    public RequestHeaderNotFounded(string header, string message) : base(GetMessage(header) + $" {message}")
    {
    }

    private static string GetMessage(string header)
    {
        return $"Header: {header} cannot be found on your request!";
    }
}