
package com.example.cpuapp.config;

import org.springframework.context.annotation.*;
import org.springframework.web.cors.*;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // allow your React dev server origin
        config.addAllowedOrigin("http://localhost:5173");
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
        // apply to all /api/** endpoints
        src.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(src);
    }
}
