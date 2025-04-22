namespace OmniSphere.LiveStreamService.API.Exceptions;

public class RequestHeaderNotFounded : Exception
{
    private static string GetMessage(string header) 
        => $"Header: {header} cannot be found on your request!";
    public RequestHeaderNotFounded(string message) : base(message)
    { }
    public RequestHeaderNotFounded(string header, Exception ex) : base(GetMessage(header), ex )
    { }
    public RequestHeaderNotFounded(string header, string message) : base(GetMessage(header) + $" {message}")
    { }
}