namespace OmniSphere.LiveStreamService.Infrastructure.Exceptions;

public class EntityNotFoundException : Exception
{
    public EntityNotFoundException(string message) : base(message)
    {
    }

    public EntityNotFoundException(string name, string identifier) : base(GetEntityExceptionMessage(name, identifier))
    {
    }

    public EntityNotFoundException(string name, string identifier, string message, Exception ex) : base(
        GetEntityExceptionMessage(name, identifier) + $" {message}", ex)
    {
    }

    public EntityNotFoundException(string name, string identifier, Exception ex) : base(
        GetEntityExceptionMessage(name, identifier), ex)
    {
    }

    private static string GetEntityExceptionMessage(string identifier)
    {
        return $"{identifier} not found!";
    }

    private static string GetEntityExceptionMessage(string name, string identifier)
    {
        return $"Entity: {name}, on identifier: {identifier}, canno't be found!";
    }
}