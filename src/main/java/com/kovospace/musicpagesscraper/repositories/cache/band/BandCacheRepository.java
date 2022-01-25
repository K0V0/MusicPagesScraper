package com.kovospace.musicpagesscraper.repositories.cache.band;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BandCacheRepository extends JpaRepository<BandCacheEntity, Long> {

    Optional<BandCacheEntity> getFirstByPlatformAndSlugAndUpdateTimeGreaterThan(
            String platform, String slug, LocalDateTime time);

    List<BandCacheEntity> getAllByPlatformAndSlugAndUpdateTimeLessThan(
            String platform, String slug, LocalDateTime time);

}
