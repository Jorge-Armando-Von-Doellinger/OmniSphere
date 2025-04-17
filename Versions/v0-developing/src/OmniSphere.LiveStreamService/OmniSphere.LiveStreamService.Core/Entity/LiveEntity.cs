namespace OmniSphere.LiveStreamService.Core.Entity;

public class LiveEntity
{
    public string LiveId { get; init; } = Guid.NewGuid().ToString();
    public required string Username { get; init; }
    public required string Title { get; set; }
    public string? Description { get; set; }
    public DateTime CreatedAt { get; } = DateTime.Now;
    public DateTime FinalizedAt { get; } = DateTime.Now;
    public DateTime? DeletedAt { get; set; }
}