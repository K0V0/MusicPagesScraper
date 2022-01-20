package com.kovospace.musicpagesscraper.repositories.cache.bands;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface BandsCacheRepository extends JpaRepository<BandsCacheEntity, Long> {

    Optional<BandsCacheEntity> getFirstByQueryAndPageAndPlatformAndUpdateTimeGreaterThan(
            String query, String page, String platform, LocalDateTime time);
}
