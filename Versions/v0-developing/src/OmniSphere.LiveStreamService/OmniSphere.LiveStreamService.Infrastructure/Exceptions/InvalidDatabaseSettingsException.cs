namespace OmniSphere.LiveStreamService.Infrastructure.Exceptions;

public class InvalidDatabaseSettingsException : Exception
{
    public InvalidDatabaseSettingsException(string message) : base($"Invalid settings! {message}")
    {
    }

    public InvalidDatabaseSettingsException() : base(
        "Invalid settings! Please, verify the settings applied and try again.")
    {
    }
}