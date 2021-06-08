package com.kovospace.musicpagesscraper.repositories;

import com.kovospace.musicpagesscraper.constants.PlatformConstants;
import com.kovospace.musicpagesscraper.interfaces.Platform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public  class PlatformRepositoryImpl
        implements PlatformRepository
{
  private HashMap<String, Platform> platformInfos;

  public PlatformRepositoryImpl() {
    this.platformInfos = PlatformConstants.PLATFORM_INFOS;
  }

  @Override
  public List<Platform> getAll() {
    return new ArrayList<Platform>(platformInfos.values());
  }
}
