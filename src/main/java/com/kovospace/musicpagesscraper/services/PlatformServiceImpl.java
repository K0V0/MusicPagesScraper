package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.interfaces.Platform;
import com.kovospace.musicpagesscraper.interfaces.Platforms;
import com.kovospace.musicpagesscraper.repositories.PlatformRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class PlatformServiceImpl
        implements PlatformService
{
  private PlatformRepository platformRepository;

  @Autowired
  public PlatformServiceImpl(
    PlatformRepository platformRepository
  ) {
    this.platformRepository = platformRepository;
  }

  @Override
  public Platforms getAll() {
    List<Platform> platforms = platformRepository.getAll();
    return new Platforms() {
      @Override
      public List<Platform> getPlatforms() {
        return platforms;
      }
      @Override
      public int getTotalCount() {
        return platforms.size();
      }
    };
  }
}