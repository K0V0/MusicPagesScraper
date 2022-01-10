package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.interfaces.Platforms;
import com.kovospace.musicpagesscraper.repositories.platform.PlatformEntity;
import com.kovospace.musicpagesscraper.repositories.platform.PlatformRepository;
import java.util.List;
import java.util.stream.Collectors;

import com.kovospace.musicpagesscraper.repositories.platform.PlatformResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class PlatformServiceImpl
        implements PlatformService
{
  private PlatformRepository platformRepository;
  private ModelMapper modelMapper;

  @Autowired
  public PlatformServiceImpl(
    PlatformRepository platformRepository,
    ModelMapper modelMapper
  ) {
    this.platformRepository = platformRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public Platforms getAll() {
    List<PlatformEntity> platforms = platformRepository.findAll();
    return new Platforms() {
      @Override public int getCurrentPageItemsCount() { return 0; }
      @Override public int getPagesCount() { return 0; }
      @Override public int getCurrentPageNum() { return 0; }
      @Override public int getTotalItemsCount() { return platforms.size(); }
      @Override public List<PlatformResponseDTO> getPlatforms() {
        return platforms
                .stream()
                .map(platformEntity -> modelMapper.map(platformEntity, PlatformResponseDTO.class))
                .collect(Collectors.toList());
      }
    };
  }
}
