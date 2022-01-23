package com.kovospace.musicpagesscraper.repositories.cache.band;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class BandCacheEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String slug;
    private String platform;

    @Lob
    private String json;

    private LocalDateTime updateTime;

    @PrePersist
    private void updateTime() {
        this.updateTime = LocalDateTime.now();
    }

    public BandCacheEntity() {}

    public BandCacheEntity(String slug, String platform, String json) {
        this.slug = slug;
        this.platform = platform;
        this.json = json;
    }

}
