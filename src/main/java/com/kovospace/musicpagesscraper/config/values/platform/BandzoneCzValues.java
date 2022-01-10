package com.kovospace.musicpagesscraper.config.values.platform;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bandzone.cz")
@Getter
@Setter
public class BandzoneCzValues {

}
