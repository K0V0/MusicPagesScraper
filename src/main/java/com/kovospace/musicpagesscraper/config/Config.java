package com.kovospace.musicpagesscraper.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
