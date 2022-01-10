package com.kovospace.musicpagesscraper.repositories.cache.bands;

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
public class BandsCacheEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String query;
    private String page;
    private String platform;

    @Lob
    private String json;

    private LocalDateTime updateTime;

    @PrePersist
    private void updateTime() {
        this.updateTime = LocalDateTime.now();
    }

}