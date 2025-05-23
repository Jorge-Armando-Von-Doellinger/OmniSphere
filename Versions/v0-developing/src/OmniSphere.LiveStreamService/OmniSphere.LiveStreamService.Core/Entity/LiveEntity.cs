namespace OmniSphere.LiveStreamService.Core.Entity;

public class LiveEntity
{
    public string LiveId { get; init; } = Guid.NewGuid().ToString();
    public required string Username { get; init; }
    public required string Title { get; set; }
    public string? Description { get; set; }
    public required string KeyAccess { get; init; }
    public DateTime CreatedAt { get; init; } = DateTime.Now;
    public DateTime? StartedAt { get; set; } = null;
    public DateTime? FinalizedAt { get; set; }
    public DateTime? DeletedAt { get; set; }

    /// <summary>
    ///     Set the finalizedAt
    /// </summary>
    public void Stop()
    {
        FinalizedAt = DateTime.Now;
    }

    /// <summary>
    ///     Verify if live is running or not
    /// </summary>
    /// <returns> true if finalizedAt be null </returns>
    public bool IsRunning()
    {
        return FinalizedAt.HasValue;
    }

    public void Finalize()
    {
        FinalizedAt = DateTime.Now;
    }

    public void Delete()
    {
        DeletedAt = DateTime.Now;
    }
}