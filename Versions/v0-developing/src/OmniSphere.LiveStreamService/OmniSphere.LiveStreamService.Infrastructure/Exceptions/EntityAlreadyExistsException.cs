namespace OmniSphere.LiveStreamService.Infrastructure.Exceptions;

public class EntityAlreadyExistsException : Exception
{
    private static string GetMessage(string name, string identifier)
        => $"Entity: {name}, on identifier: {identifier}, already exists!";
    public EntityAlreadyExistsException(string message) : base(message)
    { }
    public EntityAlreadyExistsException(string name, string identifier) : base(GetMessage(name, identifier))
    { }

    public EntityAlreadyExistsException(string name, string identifier, string message) : base(GetMessage(name, identifier) + $" {message}")
    { }
    
    public EntityAlreadyExistsException(string name, string identifier, string message, Exception ex) : base(GetMessage(name, identifier) + $" {message}", ex)
    { }
    public EntityAlreadyExistsException(string name, string identifier, Exception ex) : base(GetMessage(name, identifier), ex)
    { }
}