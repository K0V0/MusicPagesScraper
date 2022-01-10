package com.kovospace.musicpagesscraper.config.values.platform;

import com.kovospace.musicpagesscraper.config.values.PlatformValues;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "freeteknomusic.org")
@Getter
@Setter
public  class FreeteknomusicOrgValues
        extends PlatformValues
{

}
