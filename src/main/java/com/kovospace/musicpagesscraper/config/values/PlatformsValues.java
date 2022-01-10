package com.kovospace.musicpagesscraper.config.values;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "platforms")
@Getter
@Setter
public class PlatformsValues {
    private Map<String, String> platforms;
}
