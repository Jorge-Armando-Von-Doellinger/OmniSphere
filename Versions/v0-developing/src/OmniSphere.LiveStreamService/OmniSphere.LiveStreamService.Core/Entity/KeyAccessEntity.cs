namespace OmniSphere.LiveStreamService.Core.Entity;

public class KeyAccessEntity
{
    public string KeyAccess { get; set; }
    public string UserId { get; set; }
    public DateTime CreatedAt { get; init; } = DateTime.UtcNow;
    public DateTime? UpdatedAt { get; set; }
    public DateTime ExpireIn { get; set; }

    public bool IsExpired()
    {
        return DateTime.UtcNow > ExpireIn;
    }
    
    public void SetExpireIn(int durationInMinutes) => ExpireIn = DateTime.UtcNow.AddMinutes(durationInMinutes);
}