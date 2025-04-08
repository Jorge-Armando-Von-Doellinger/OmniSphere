namespace OmniSphere.LiveStreamService.Core.Entity;

public class LiveEntity
{
    public string LiveId { get; set; } = Guid.NewGuid().ToString();
    public required string Title { get; init; }
    public required string Username { get; init; }
    public string? Description { get; set; }
    public DateTime CreatedAt { get; } = DateTime.Now;
    public DateTime FinalizedAt { get; } = DateTime.Now;
    public DateTime? DeletedAt { get; set; }
}