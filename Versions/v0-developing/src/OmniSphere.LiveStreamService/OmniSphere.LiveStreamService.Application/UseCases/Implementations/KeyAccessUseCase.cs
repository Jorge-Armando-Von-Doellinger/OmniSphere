using OmniSphere.LiveStreamService.Application.UseCases.Interfaces;
using OmniSphere.LiveStreamService.Core.Entity;
using OmniSphere.LiveStreamService.Core.Interfaces.Repository;
using OmniSphere.LiveStreamService.Core.Interfaces.Services.Generator;

namespace OmniSphere.LiveStreamService.Application.UseCases.Implementations;

public class KeyAccessUseCase : IKeyAccessUseCase
{
    private readonly IKeyAccessGenerator _keyAccessGenerator;
    private readonly IKeyAccessRepository _repository;
    private readonly ICachedKeyAccessRepository _cachedRepository;

    public KeyAccessUseCase(IKeyAccessGenerator keyAccessGenerator,
        IKeyAccessRepository repository,
        ICachedKeyAccessRepository cachedRepository)
    {
        _keyAccessGenerator = keyAccessGenerator;
        _repository = repository;
        _cachedRepository = cachedRepository;
    }
    public async Task<KeyAccessEntity> GenerateKeyAccessAsync(int durationInDays, string userId)
    {
        var durationInMinutes = (int) TimeSpan.FromDays(durationInDays).TotalMinutes;
        var key = await _keyAccessGenerator.GenerateKeyAccess(durationInMinutes, userId);
        await _repository.AddAsync(key);
        return key;
    }

    public async Task<KeyAccessEntity> GenerateTemporaryKeyAccessAsync(int durationInMinutes, string userId)
    {
        var key = await _keyAccessGenerator.GenerateKeyAccess(durationInMinutes, userId);
        await _cachedRepository.AddAsync(key);
        return key;
    }

    public async Task<bool> ValidateKeyAccessAsync(string keyAccess, string userId)
    {
        var key = await _repository.GetByKeyAccess(keyAccess);
        return key.IsExpired() == false;
    }

    public async Task<bool> ValidateTemporaryKeyAccessAsync(string keyAccess, string userId)
    {
        var exists = await _cachedRepository.ExistsAsync(keyAccess, userId);
        return exists;
    }
}