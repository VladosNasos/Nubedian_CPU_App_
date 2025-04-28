package com.example.cpuapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootApplication
public class CpUappApplication {

    public static void main(String[] args) {
        SpringApplication.run(CpUappApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/api/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                        .allowCredentials(true);
            }
        };
    }
}