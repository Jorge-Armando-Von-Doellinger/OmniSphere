namespace OmniSphere.LiveStreamService.Infrastructure.Exceptions;

public class FailedToConnectOnDatabaseException : Exception
{
    public FailedToConnectOnDatabaseException(string message) : base(message) { }

    public FailedToConnectOnDatabaseException() : base("Failed to connect to database! Please, verify the settings applied and try again.") { }
}