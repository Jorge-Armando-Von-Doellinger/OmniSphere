namespace OmniSphere.LiveStreamService.Application.Models;

public class LiveModel
{
    public required string Username { get; init; }
    public required string Title { get; set; }
    public string? Description { get; set; }
    public required string KeyAccessToken { get; init; }
}