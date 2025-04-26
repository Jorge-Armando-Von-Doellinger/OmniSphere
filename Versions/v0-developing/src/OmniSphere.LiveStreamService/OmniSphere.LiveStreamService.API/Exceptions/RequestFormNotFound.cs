namespace OmniSphere.LiveStreamService.API.Exceptions;

public class RequestFormNotFound : Exception
{
    public RequestFormNotFound(string message) : base(message)
    {
    }

    public RequestFormNotFound(string form, Exception ex) : base(GetMessage(form), ex)
    {
    }

    public RequestFormNotFound(string form, string message) : base(GetMessage(form) + $" {message}")
    {
    }

    private static string GetMessage(string form)
    {
        return $"{form} cannot be found on your form request!";
    }
}