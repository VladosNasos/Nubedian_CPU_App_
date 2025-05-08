package com.example.cpuapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration cfg = new CorsConfiguration();

        cfg.addAllowedOriginPattern("http://localhost:*");

        cfg.setAllowCredentials(true);
        cfg.addAllowedHeader("*");
        cfg.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", cfg);

        return new CorsFilter(source);
    }
}
